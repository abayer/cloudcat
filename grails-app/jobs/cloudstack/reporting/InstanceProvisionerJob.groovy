package cloudstack.reporting

import static com.google.common.base.Throwables.propagate
import static com.google.common.collect.Iterables.getOnlyElement
import org.jclouds.domain.LoginCredentials
import org.jclouds.compute.RunNodesException
import org.apache.commons.logging.LogFactory
import grails.plugin.executor.PersistenceContextExecutorWrapper
import java.util.concurrent.Executors
import java.util.concurrent.Callable
import org.jclouds.cloudstack.compute.strategy.CloudStackComputeServiceAdapter
import org.jclouds.cloudstack.compute.options.CloudStackTemplateOptions
import org.jclouds.scriptbuilder.domain.Statements
import static org.jclouds.scriptbuilder.domain.Statements.newStatementList
import org.jclouds.compute.options.RunScriptOptions
import org.jclouds.cloudstack.options.ListAccountsOptions

class InstanceProvisionerJob {
    static transactional = true
    private static final log = LogFactory.getLog(this)

    def executorService

    def concurrent = false

    static triggers = {
    simple name: 'provisionerTrigger', startDelay:30000, repeatInterval:30000, repeatCount:-1
    }

    def execute() {
        def csCfg = CloudStackConfig.where { lastUpdated >= max(lastUpdated) }.find()
        
        def jcloudsConn = new JCloudsConnection(endPointUrl:csCfg.endPointUrl,
                                                identity:csCfg.apiKey,
                                                credential:csCfg.secretKey)
        
        def ctx = jcloudsConn.ctx()
        def computeSvc = ctx.getComputeService()


        try {
            def unProvCount = ProvisionedInstance.countByProvisionStatus(0)
            def provCount = ProvisionedInstance.countByProvisionStatus(1)
            log.debug("Current count in unprovisioned: ${unProvCount}")
            log.debug("Current count in provisioning: ${provCount}")

            if (unProvCount == 0) {
                log.debug("No instances to provision")
            } else if (provCount < csCfg.maxProvDestroy) { 
                log.debug("Provisioning the next round of ${csCfg.maxProvDestroy - provCount}")

                def templates = [:]
                def userCtx = [:]
                // Provision the next 5.
                ProvisionedInstance.findAllByProvisionStatus(0,[max: (csCfg.maxProvDestroy - provCount), sort: 'id']).each { i ->
                    log.debug("...provisioning ${i.hostname}")
                    if (!i.isAttached()) {
                        i.attach()
                    }
                    i.provisionStatus = 1
                    i = i.merge(flush:true)

                    try { 
                        if (templates["${i.provisionedInstanceGroup.id}_${i.role}"] == null) {
                            templates["${i.provisionedInstanceGroup.id}_${i.role}"] = buildTemplate(csCfg, ctx,
                                                                                                    i.provisionedInstanceGroup,
                                                                                                    i)
                        }
                        if (userCtx[i.provisionedInstanceGroup.username] == null) {
                            def keys = getAccountKeys(ctx, i.provisionedInstanceGroup.username)
                            userCtx[i.provisionedInstanceGroup.username] = new JCloudsConnection(endPointUrl:csCfg.endPointUrl,
                                                                                                 identity:keys[0],
                                                                                                 credential:keys[1]).ctx()
                        }
                        executorService.execute { 
                            commonProvision(templates["${i.provisionedInstanceGroup.id}_${i.role}"], i.provisionedInstanceGroup.id, i.id, userCtx[i.provisionedInstanceGroup.username], ctx, csCfg)
                        }
                        log.debug("past executor service")

                    } catch (Exception e) {
                        log.error("inner provisioner error: ${e} : ${e.getStackTrace()}")
                        i.provisionStatus = 2
                        i.errorMsg = "${e}"
                    }
                    Thread.sleep(4000)
                }
            } else {
                log.debug("Already at max")
            }
            
        } catch (Exception e) {
            log.error("provisioner error: ${e} : ${e.getStackTrace()}")
        }

        
        try {
            ProvisionedInstanceGroup.findAllByProvisionStatus(6).each { g ->
                g.provisionedInstances.each { pi ->
                    if (!pi.isAttached()) {
                        pi.attach()
                    }
                    if (pi.provisionStatus == 3) {
                        if (pi.instanceId != null) { 
                            log.debug("Destroying instance ${pi.hostname} from cancelled group ${g.shortName}")
                            pi.provisionStatus = 4
                        } else {
                            log.debug("Cancelling instance ${pi.hostname} from cancelled group ${g.shortName}")
                            pi.provisionStatus = 7
                        }
                        pi.merge(flush:true)
                    }
                    if (pi.provisionStatus == 0) {
                        log.debug("Cancelling creation of instance ${pi.hostname} from cancelled group ${g.shortName}")
                        pi.provisionStatus = 7
                        pi.merge(flush:true)
                    }
                }
                
            }
        } catch (Exception e) {
            log.error("cancel error: ${e} : ${e.getStackTrace()}")
        }
       
        try {
            def undestroyedCount = ProvisionedInstance.countByProvisionStatus(4)
            def destroyingCount = ProvisionedInstance.countByProvisionStatus(5)
            log.debug("Current count in undestroyed: ${undestroyedCount}")
            log.debug("Current count in destroying: ${destroyingCount}")

            if (undestroyedCount == 0) {
                log.debug("No instances to destroy")
            } else if (destroyingCount < csCfg.maxProvDestroy) { 
                log.debug("destroying the next round of ${csCfg.maxProvDestroy - destroyingCount}")
                // Provision the next 5.
                ProvisionedInstance.findAllByProvisionStatus(4,[max: (csCfg.maxProvDestroy - destroyingCount)]).each { i ->
                    if (!i.isAttached()) {
                        i.attach()
                    }
                    if (i.instanceId == null) {
                        log.debug("...marking non-existent instance ${i.hostname} as destroyed")
                        i.provisionStatus = 6
                        i.merge(flush:true)
                    } else { 
                        log.debug("...destroying ${i.hostname}")
                        i.provisionStatus = 5
                        i = i.merge(flush:true)
                        executorService.execute { 
                            computeSvc.destroyNode(i.instanceId)
                            i.provisionStatus = 6
                            i.merge(flush:true)
                        }
                        log.debug("past destroy executor service")
                        Thread.sleep(1000)
                    }
                }
            } else {
                log.debug("Already at destroy max")
            }
            
        } catch (Exception e) {
            log.error("destroy error: ${e} : ${e.getStackTrace()}")
        }

        
        // execute job
    }

    def getAccountKeys(ctx, username) {
        def acct = ctx.getGlobalContext().getApi().getAccountClient().listAccounts(ListAccountsOptions.Builder.name(username)).first()

        return [acct.getUsers().first().apiKey, acct.getUsers().first().secretKey]
    }
    
    def buildTemplate(csCfg, ctx, g, i) {
        def computeSvc = ctx.getComputeService()
        def templateBuilder = computeSvc.templateBuilder()

        templateBuilder.imageId(g.template.templateId)

        if (i.role == "Standard") {
            templateBuilder.hardwareId(g.primaryServiceOffering.offeringId)
        } else if (i.role == "Alternate") {
            templateBuilder.hardwareId(g.alternateServiceOffering.offeringId)
        }

        log.debug("about to do network bits")
        try {
            def csto
            if (csCfg.setupStaticNat) {
                csto = CloudStackTemplateOptions.Builder.setupStaticNat(true)
            } else {
                csto = CloudStackTemplateOptions.Builder.setupStaticNat(false)
            }

            if (csCfg.networkId != null && csCfg.networkId != "") {
                csto.networkId(csCfg.networkId)
            }
            templateBuilder.options(csto)
        } catch (Exception e) {
            log.error("Error setting template options: ${e}, ${e.getStackTrace()}")
            i.provisionStatus = 2
            i.errorMsg = e
            i.merge(flush:true)
            return null
        }
        log.debug("did network bits")
        def template = templateBuilder.build()

        if (csCfg.instanceAdminUser != null) { 
            LoginCredentials lc = LoginCredentials.builder().user(csCfg.instanceAdminUser).password(csCfg.instanceAdminPassword).build()
            template.getOptions().overrideLoginCredentials(lc)
            log.debug("Setting creds for ${csCfg.instanceAdminUser}")
        }

        return template

    }
    
    def commonProvision(template, gId, iId, ctx, adminCtx, csCfg) {
        log.debug("In commonProvision")
        // 5 here is how many more times to try
        try {
            def retriesLeft = 5
            while (retriesLeft > 0) { 
                if (provisionWithRetry(template, ctx, adminCtx, csCfg, gId, iId, retriesLeft)) {
                    break
                } else {
                    retriesLeft--
                }
            }
        } catch (Exception e) {
            log.error("provisionWithRetry error: ${e}, ${e.getStackTrace()}")
            def i = ProvisionedInstance.get(iId)
            i.provisionStatus = 2
            i.errorMsg = e
            i.merge(flush:true)
        }
    }
    
    def provisionWithRetry(template, ctx, adminCtx, csCfg, provisionGroupId, provisionedInstanceId, retriesLeft) {
        def provisionedInstance = ProvisionedInstance.get(provisionedInstanceId)
        def provisionGroup = ProvisionedInstanceGroup.get(provisionGroupId)
        // Try to provision
        // Return 1 if successful, 0 otherwise
        log.debug("In provisionWithRetry with group ${provisionGroup} and instance ${provisionedInstance.role}, retry ${retriesLeft}")

        def csAdapter = ctx.utils().injector().getInstance(CloudStackComputeServiceAdapter.class)
        def computeSvc = ctx.getComputeService()
        def templateBuilder = computeSvc.templateBuilder()

        log.debug("about to create...")
        def vm = csAdapter.createNodeWithGroupEncodedIntoName(provisionGroup.shortName, provisionedInstance.instanceName, template)

        if (vm.getNode().state.toString().equals("Error")) { 
            log.debug("I got an error")
            adminCtx.getGlobalContext().getApi().getVirtualMachineClient().destroyVirtualMachine(vm.getNode().id)       
            if (retriesLeft == 1) {
                provisionedInstance.provisionStatus = 2
                provisionedInstance.errorMsg = adminCtx.getGlobalContext().getApi().getAsyncJob(vm.getNode().jobId).error.errorText
                provisionedInstance.save(flush:true)
                log.error("Errors when creating instance ${provisionedInstance.instanceName} for group ${provisionGroup.shortName}: ${provisionedInstance.errorMsg}")
            }
            return 0
        } else {
            try {
                def runScript
                if (provisionGroup.initScript != null) {
                    if (csCfg.instanceAdminUser != null) { 
                        runScript = Statements.exec(provisionGroup.initScript)
                        
                        def execResp = computeSvc.runScriptOnNode(vm.getNodeId(), runScript)
                        if (execResp.getExitStatus() > 0) {
                            log.debug("runScript error")
                            adminCtx.getGlobalContext().getApi().getVirtualMachineClient().destroyVirtualMachine(vm.getNode().id)       
                            if (retriesLeft == 1) {
                                provisionedInstance.provisionStatus = 2
                                provisionedInstance.errorMsg = "${execResp.getError()}: ${execResp.getOutput()}"
                                provisionedInstance.save(flush:true)
                                log.error("Errors when running script on instance ${provisionedInstance.instanceName} for group ${provisionGroup.shortName}: ${provisionedInstance.errorMsg}")
                            }
                            return 0
                        }
                    } else {
                        log.warning("Can not run init script on instance ${provisionedInstance.instanceName}: no instance admin user specified")
                    }
                }
            } catch (Exception e) {
                log.debug("runScript exception: ${e}")
                adminCtx.getGlobalContext().getApi().getVirtualMachineClient().destroyVirtualMachine(vm.getNode().id)       
                if (retriesLeft == 1) {
                    provisionedInstance.provisionStatus = 2
                    provisionedInstance.errorMsg = e
                    provisionedInstance.save(flush:true)
                    log.error("Errors when running script on instance ${provisionedInstance.instanceName} for group ${provisionGroup.shortName}: ${provisionedInstance.errorMsg}")
                }
                return 0
            }
        }

        log.debug("Hey, things are created for ${vm.getNode().id}. That's good.")
        provisionedInstance.instanceName = vm.getNode().getName()
        log.debug("instance name for ${vm.getNode().id} set")
        provisionedInstance.instanceId = vm.getNode().id
        log.debug("instance id for ${vm.getNode().id} set")
        provisionedInstance.publicIp = vm.getNode().nics.collect { n -> n.getIPAddress() }.first()
        log.debug("instance ips for ${vm.getNode().id} set")
        provisionedInstance.provisionStatus = 3
        log.debug("And now I'm saving for ${vm.getNode().id}")
        provisionedInstance.save(flush:true)
        return 1
    }

    def getConnectionAddresses(nodeMetadata) {
        if (nodeMetadata.getPublicAddresses().size() > 0) {
            return nodeMetadata.getPublicAddresses().toArray(new String[nodeMetadata.getPublicAddresses().size()])
        } else {
            return nodeMetadata.getPrivateAddresses().toArray(new String[nodeMetadata.getPrivateAddresses().size()])
        }
    }

    def destroyBadNodes(RunNodesException e, computeSvc) {
        e.getNodeErrors().each { k, v ->
            computeSvc.destroyNode(k.getId())
        }
    }

}
