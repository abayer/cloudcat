package cloudstack.reporting

class Instance {
    String instanceId
    String account
    long cpuCount
    String cpuUsed
    Date created
    String hostId
    long memory
    String name
    String publicIPs
    String rootDeviceId
    String serviceOfferingName
    String state
    String templateId
    String templateName
    Date lastUpdated

    static hasMany = [reportRuns:ReportRun]

    static belongsTo = ReportRun

    static constraints = {
        cpuUsed(nullable:true)
        hostId(nullable:true)
    }
}
