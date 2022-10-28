package tech.brito.parkingsystem.exception;

import static tech.brito.parkingsystem.core.Constants.MSG_VEHICLE_NOT_FOUND;

public class VehicleNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(String message) {
        super(message);
    }

    public VehicleNotFoundException() {
        this(MSG_VEHICLE_NOT_FOUND);
    }
}
