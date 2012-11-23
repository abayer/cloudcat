package cloudstack.reporting

class ServiceOffering {
    String offeringId
    String name
    String displayText
    Date created
    String domain
    String domainId
    int cpuNumber
    int cpuSpeed
    int memory
    boolean haSupport
    String storageType
    boolean defaultUse
    boolean systemOffering
    boolean cpuUseLimited
    long networkRate
    boolean systemVmType
    String tags

    boolean stillActive
    
    Date lastUpdated

    static hasMany = [instances:Instance]
    
    static constraints = {
        name()
        displayText(nullable:true)
        cpuNumber()
        cpuSpeed()
        memory()
        storageType()
        created()
        offeringId()
        domain(nullable:true)
        domainId(nullable:true)
        haSupport()
        defaultUse()
        systemOffering()
        cpuUseLimited()
        networkRate(nullable:true)
        systemVmType()
        tags(nullable:true)
    }
}
