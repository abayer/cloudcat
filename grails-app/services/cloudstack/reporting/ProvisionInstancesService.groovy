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


class ProvisionInstancesService {
    static transactional = true
    private static final log = LogFactory.getLog(this)

    def executorService

    def destroySingleInstance(id) {
        def i = ProvisionedInstance.get(id)
        log.warn("destroySingleInstance: got ${i}")

        i.provisionStatus = 4
        i.save(flush:true)
    }
    
    def destroyInstances(id) {
        def g = ProvisionedInstanceGroup.get(id)
        log.warn("destroyInstances: got ${g}")

        g.provisionStatus = 4
        g.save(flush:true)
        //        g.refresh()
        log.warn("group status: ${g.provisionStatus}")
        executorService.execute { 
            ProvisionedInstance.findAllByProvisionStatusBetweenAndProvisionedInstanceGroup(2, 4, g).each { i ->
                i.provisionStatus = 4
                i.save(flush:true)
            }
        }
    }
    

    def cancelInstances(id) {
        def g = ProvisionedInstanceGroup.get(id)
        log.warn("cancelInstances: got ${g}")

        g.provisionStatus = 6
        g.whenDecommissioned = new Date()
        g.save(flush:true)
        //        g.refresh()
    }
    

    def provisionInstances(id) {
        def csCfg = CloudStackConfig.where { lastUpdated >= max(lastUpdated) }.find()
        
        def jcloudsConn = new JCloudsConnection(endPointUrl:csCfg.endPointUrl,
                                                identity:csCfg.apiKey,
                                                credential:csCfg.secretKey)
        
        def ctx = jcloudsConn.ctx()
        def g = ProvisionedInstanceGroup.get(id)
        log.warn("got ${g}")
        // Check to make sure g actually exists
        if (g == null) {
            // Throw something
        }

        // Check the status, make sure we can actually provision
        if (g.provisionStatus != 0) {
            // throw something probably?
        }

        g.provisionStatus = 1
        g.save(flush:true)
        log.warn("Setting group status to 1 in theory - ${g.provisionStatus}")
        if (g.secondaryCount > 0 ||  g.primaryCount > 1) { 
            if (g.secondaryCount != null && g.secondaryCount != 0) {
                log.warn("in secondary spawn")
                for (altCnt in 1..(g.secondaryCount)) {
                    createInstanceRecord(g, "Alternate", altCnt)
                }
            }
            
            for (cnt in 1..(g.primaryCount)) {
                createInstanceRecord(g, "Standard", cnt + g.secondaryCount)
            }
        } else {
            createInstanceRecord(g, "Standard", null)
        }
        g.save(flush:true)

        log.warn("Current count in unprovisioned: ${ProvisionedInstance.countByProvisionStatusAndProvisionedInstanceGroup(0, g)}")
        log.warn("Current count in provisioning: ${ProvisionedInstance.countByProvisionStatusAndProvisionedInstanceGroup(1, g)}")
    }


    def createInstanceRecord(g, role, cnt) {
        log.warn("commonProvision - ${g.provisionStatus}")
        def i = new ProvisionedInstance()
        i.role = role
        i.provisionStatus = 0
        if (cnt != null) { 
            i.instanceName = "${g.shortName}-${g.offset + cnt}"
        } else {
            i.instanceName = "${g.shortName}"
        }
        i.hostname = "${i.instanceName}"
        i.save(flush:true)
        g.addToProvisionedInstances(i)
        g.save(flush:true)
        log.warn("Added provisioned instance")

        log.warn("added instance - ${g.provisionStatus}: ${i.hostname}, ${i.id}")
    }
}
