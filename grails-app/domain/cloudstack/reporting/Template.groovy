package cloudstack.reporting

class Template {
    String templateId
    String displayText
    String domain
    String domainId
    String account
    String accountId
    String zone
    String zoneId
    String OSType
    String OSTypeId
    String name
    String type
    String status
    String format
    String hypervisor
    Long size
    Date created
    Date removed
    boolean crossZones
    boolean bootable
    boolean extractable
    boolean featured
    boolean ispublic
    boolean ready
    boolean passwordEnabled
    String jobId
    String jobStatus
    String checksum
    String hostId
    String hostName
    String sourceTemplateId
    String templateTag

    static hasMany = [instances: Instance]
    
    Date lastUpdated
    boolean stillActive
    
    static constraints = {
        templateTag(nullable:true)
        sourceTemplateId(nullable:true)
        removed(nullable:true)
        accountId(nullable:true)
        account(nullable:true)
        checksum(nullable:true)
        hostName(nullable:true)
        jobStatus(nullable:true)
        jobId(nullable:true)
        hostId(nullable:true)
    }
}
