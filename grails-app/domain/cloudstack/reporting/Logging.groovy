package cloudstack.reporting

class Logging {
    
    String msg
    String user
    LoggingCategory category
    Date dateCreated
    
    static constraints = {
        user(nullable:true)
        category(blank:false)
    }

    static mapping = {
        msg(type:'text')
    }
}
