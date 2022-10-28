package tech.brito.parkingsystem.exception;

import java.util.UUID;

public class RoleNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        this("Role not found!");
    }
}
