package tech.brito.parkingsystem.exception;

public class EntityNotFoundException extends DomainRuleException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
