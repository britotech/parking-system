package tech.brito.parkingsystem.enums;

public enum RoleType {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");

    private final String value;

    private RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
