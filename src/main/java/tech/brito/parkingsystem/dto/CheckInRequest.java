package tech.brito.parkingsystem.dto;

import lombok.Getter;
import lombok.Setter;
import tech.brito.parkingsystem.model.Vehicle;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CheckInRequest {

    @NotBlank
    private String color;

    @NotBlank
    private String model;

    @NotBlank
    private String license;

    public Vehicle toEntity() {
        var vehicle = new Vehicle();
        vehicle.setColor(color);
        vehicle.setModel(model);
        vehicle.setLicense(license);

        return vehicle;
    }
}
