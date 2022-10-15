package tech.brito.parkingsystem.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.brito.parkingsystem.dto.CheckInRequest;
import tech.brito.parkingsystem.dto.CheckOutRequest;
import tech.brito.parkingsystem.dto.VehicleResponse;
import tech.brito.parkingsystem.exception.DomainRuleException;
import tech.brito.parkingsystem.exception.NoParkingSpacesAvailableException;
import tech.brito.parkingsystem.exception.VehicleNotFoundException;
import tech.brito.parkingsystem.model.Vehicle;
import tech.brito.parkingsystem.service.ParkingService;
import tech.brito.parkingsystem.service.VehicleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static tech.brito.parkingsystem.core.Constants.MSG_VEHICLE_NOT_FOUND;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final VehicleService vehicleService;

    public ParkingController(ParkingService parkingService, VehicleService vehicleService) {
        this.parkingService = parkingService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleResponse>> parkedVehicles() {
        List<Vehicle> parkedVehicles = vehicleService.findAllParkedVehicles();
        var parkedVehiclesResponse = parkedVehicles.stream().map(v -> new VehicleResponse(v)).collect(Collectors.toList());
        return ResponseEntity.ok(parkedVehiclesResponse);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable UUID id) {
        var vehicle = vehicleService.findByIdOrThrow(id);
        return ResponseEntity.ok(new VehicleResponse(vehicle));
    }

    @PostMapping("/checkin")
    public ResponseEntity<Object> checkIn(@RequestBody @Valid CheckInRequest checkInRequest) {
        if (!parkingService.CheckParkingAvailability()) {
            throw new NoParkingSpacesAvailableException();
        }

        var vehicle = parkingService.checkIn(checkInRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new VehicleResponse(vehicle));
    }

    @PostMapping("/checkout")
    public ResponseEntity<VehicleResponse> checkOut(@RequestBody @Valid CheckOutRequest checkOutRequest) {

        try {

            var vehicle = vehicleService.findByIdOrThrow(checkOutRequest.getVehicleId());
            if (Objects.nonNull(vehicle.getExitDate())) {
                throw new DomainRuleException("vehicle has already checked out.");
            }

            return ResponseEntity.ok(new VehicleResponse(parkingService.checkOut(vehicle)));
        } catch (VehicleNotFoundException ex) {
            throw new DomainRuleException(MSG_VEHICLE_NOT_FOUND);
        }
    }
}
