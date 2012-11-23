package cloudstack.reporting


class ProvisionedInstance {
    String hostname
    String instanceName
    String instanceId
    String publicIp
    String role
    
    int provisionStatus
    String errorMsg

    Date dateCreated
    
    
    ProvisionedInstanceGroup provisionedInstanceGroup

    static auditable = true
    
    static belongsTo = [ProvisionedInstanceGroup]
    
    static constraints = {
        hostname(nullable:true)
        instanceName(nullable:true)
        publicIp(nullable:true)
        instanceId(nullable:true)
        errorMsg(nullable:true)
        role(nullable:true)
        dateCreated(nullable:true)
    }

    static mapping = {
        errorMsg type:'text'
    }
}
