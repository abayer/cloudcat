package cloudstack.reporting

class ReportRun {
    Date dateCreated
    
    static hasMany = [instances:Instance]

}
