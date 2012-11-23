package cloudstack.reporting

class Alert {
    String alertId;
    String description
    Date sent
    String type

    static constraints = {
        type(nullable:true)
    }
}
