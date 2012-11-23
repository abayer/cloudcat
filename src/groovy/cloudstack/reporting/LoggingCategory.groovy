package cloudstack.reporting

public enum LoggingCategory {
    ERROR(0), ACTION(1), OPERATION(2)

    private final Integer value

    LoggingCategory(Integer value) {
        this.value = value
    }

    Integer getId() {
        value
    }
}