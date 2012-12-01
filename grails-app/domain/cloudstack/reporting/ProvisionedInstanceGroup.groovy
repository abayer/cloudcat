package cloudstack.reporting

class ProvisionedInstanceGroup {

    String username
    String shortName

    int primaryCount
    int secondaryCount
    int offset

    boolean sendEmail
    
    String initScript
    
    String description

    int provisionStatus
    
    Template template
    ServiceOffering primaryServiceOffering
    ServiceOffering alternateServiceOffering

    SecRole team
    
    Date dateCreated
    Date lastUpdated
    Date expirationTime
    Date whenDecommissioned

    static auditable = true
    
    static hasMany = [provisionedInstances:ProvisionedInstance]
    
    static constraints = {
        shortName(matches:'[a-zA-Z][a-zA-Z0-9-]+[a-zA-Z0-9]',
                  validator: { val, obj -> return obj.noExistingInstances(obj.shortName, obj.primaryCount + obj.secondaryCount, obj.offset, obj.id) })

        alternateServiceOffering(nullable:true)
        primaryCount(min:1)
        secondaryCount(nullable:true)
        expirationTime(nullable:true)
        initScript(nullable:true)
        team(nullable:true)
        whenDecommissioned(nullable:true)
    }

    def noExistingInstances(testName, testCount, testOffset, testId) {

        for (cnt in 1..testCount) {
            def g = ProvisionedInstanceGroup.get(testId)
            if (ProvisionedInstance.findByInstanceNameLikeAndProvisionStatusNotEqualAndProvisionedInstanceGroupNotEqual("${testName}-${testOffset + cnt}%", 9, g)) {
                return false
            }
            if (ProvisionedInstance.findByInstanceNameAndProvisionStatusNotEqualAndProvisionedInstanceGroupNotEqual("${testName}", 9, g)) {
                return false
            }
        }
        
        return true
    }
}
