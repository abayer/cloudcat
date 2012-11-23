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
import groovy.time.*

class ProvisionedInstanceGroupStatusJob {
    static transactional = true
    private static final log = LogFactory.getLog(this)

    def executorService

    def mailService
    
    def concurrent = false

    static triggers = {
    simple name: 'statusTrigger', startDelay:45000, repeatInterval:30000, repeatCount:-1
    }


    def execute() {
        if (ProvisionedInstanceGroup.countByProvisionStatus(1) > 0) { 
            def csCfg = CloudStackConfig.where { lastUpdated >= max(lastUpdated) }.find()
            ProvisionedInstanceGroup.findAllByProvisionStatus(1).each { g ->
                try {
                    log.debug("Entering status check for ${g.shortName} with status ${g.provisionStatus}")
                    def provCount = ProvisionedInstance.countByProvisionStatusAndProvisionedInstanceGroup(1, g)
                    def unProvCount = ProvisionedInstance.countByProvisionStatusAndProvisionedInstanceGroup(0, g)
                    log.debug("Current count in unprovisioned: ${unProvCount}")
                    log.debug("Current count in provisioning: ${provCount}")
                    
                    if ((provCount > 0) || (unProvCount > 0)) {
                        log.debug("instances are still provisioning")
                    } else {
                        // If there are instances with errors...
                        if (ProvisionedInstance.countByProvisionStatusAndProvisionedInstanceGroup(2, g) > 0) {
                            log.debug("setting group status to 2")
                            g.provisionStatus = 2
                        } else {
                            log.debug("setting group status to 3")
                            g.provisionStatus = 3
                        }
                        if (!g.isAttached()) { 
                            g = g.merge(flush:true)
                        } else {
                            g.save(flush:true)
                        }
                        
                        if (g != null) { 
                            if (g.hasErrors()) { 
                                g.errors.allErrors.each {
                                    log.warn("error saving group ${g.id} error: ${it}")
                                }
                            } else if (g.sendEmail) {
                                mailService.sendMail {
                                    from csCfg.alertEmailFrom
                                    to "${g.username}@${csCfg.emailDomain}"
                                    subject "[CloudCat] Instance group ${g.shortName}: ${ProvisionedInstanceStatus.groupStatuses[g.provisionStatus]}"
                                    html view: "/email/provisionedGroupResult", model: [group: g]
                                }
                                
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("status check error: ${e.getMessage()} : ${e.getStackTrace()}")
                }
            }
        } else {
            log.debug("No instance groups are in provisioning")
        }
        
        if (ProvisionedInstanceGroup.countByProvisionStatus(4) > 0) { 
            ProvisionedInstanceGroup.findAllByProvisionStatus(4).each { g ->
                try {
                    log.debug("Entering destroy status check for ${g.shortName} with status ${g.provisionStatus}")
                    def undestroyedCount = ProvisionedInstance.countByProvisionStatusBetweenAndProvisionedInstanceGroup(4, 5, g)
                    log.debug("Current count in undestroyed: ${undestroyedCount}")
                    
                    if (undestroyedCount > 0) {
                        log.debug("instances are still destroying")
                    } else {
                        g.provisionStatus = 5
                        g.whenDecommissioned = new Date()
                        g = g.merge(flush:true)
                        if (g.hasErrors()) { 
                            g.errors.allErrors.each {
                                log.warn("error saving group ${g.id} error: ${it}")
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("status check error: ${e.getMessage()} : ${e.getStackTrace()}")
                }
            }
        } else {
            log.debug("No instance groups are in destroying")
        }
        // execute job
    }
}
