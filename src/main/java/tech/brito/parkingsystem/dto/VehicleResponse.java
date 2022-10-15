package tech.brito.parkingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import tech.brito.parkingsystem.model.Vehicle;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static tech.brito.parkingsystem.core.Constants.DATE_TIME_FORMAT_UTC;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleResponse {

    private UUID id;

    private String color;

    private String model;
    private String license;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT_UTC)
    private OffsetDateTime entryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT_UTC)
    private OffsetDateTime exitDate;

    private BigDecimal billTotal;

    public VehicleResponse(Vehicle vehicle) {
        id = vehicle.getId();
        color = vehicle.getColor();
        model = vehicle.getModel();
        license = vehicle.getLicense();
        entryDate = vehicle.getEntryDate();
        exitDate = vehicle.getExitDate();
        billTotal = vehicle.getBillTotal();
    }
}
