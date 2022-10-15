package tech.brito.parkingsystem.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/invalid-data", "Invalid data"),
    SYSTEM_FAILURE("/system-failure", "System failure"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),

    UNPROCESSABLE_ENTITY("/unprocessable_entity", "Unprocessable entity"),
    ACCESS_DENIED("/access-denied", "Access denied"),
    BUSINESS_RULE_VIOLATION("/business-rule-violation", "Business rule violation");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://brito-parking-system" + path;
        this.title = title;
    }
}
