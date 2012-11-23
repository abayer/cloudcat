package cloudstack.reporting

import org.apache.commons.logging.LogFactory


class PruneDestroyedProvisionInstanceGroupsJob {
    private static final log = LogFactory.getLog(this)

    def concurrent = false
    
    static triggers = {
    simple name: 'pruneTrigger', startDelay:300000, repeatInterval:3600000, repeatCount:-1
    }

    def execute() {
        log.warn("Running prune group job")
        ProvisionedInstanceGroup.findAllByLastUpdatedLessThanAndProvisionStatusGreaterThanEquals(new Date() - 1, 5).each { g ->
            log.warn("Pruning destroyed or cancelled group ${g.shortName}")
            def pruneIds = g.provisionedInstances.collect { it.id }
            pruneIds.each { pruneId ->
                def i = ProvisionedInstance.get(pruneId)
                log.warn("Pruning destroyed instance ${i.instanceName}")
                g.removeFromProvisionedInstances(i)
                i.delete()
            }
            g.delete(flush:true)
        } 
        // execute job
    }
}
