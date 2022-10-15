package tech.brito.parkingsystem.exception;

public class NoParkingSpacesAvailableException extends DomainRuleException {

    private static final long serialVersionUID = 1L;

    public NoParkingSpacesAvailableException(String message) {
        super(message);
    }

    public NoParkingSpacesAvailableException() {
        this("There are no parking spaces available.");
    }
}
