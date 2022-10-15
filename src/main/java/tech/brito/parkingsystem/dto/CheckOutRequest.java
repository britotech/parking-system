package tech.brito.parkingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class CheckOutRequest {

    @NotNull
    private UUID vehicleId;
}
