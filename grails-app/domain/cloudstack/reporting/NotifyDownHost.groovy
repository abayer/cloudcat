package cloudstack.reporting

class NotifyDownHost {
    String hostId
    String hostName
    Date downSince
    Date upSince
    String currentState
    
    static constraints = {
        downSince(nullable:true)
        upSince(nullable:true)
        hostName(nullable:true)
    }
}
