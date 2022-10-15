package tech.brito.parkingsystem.service;

import org.springframework.stereotype.Service;
import tech.brito.parkingsystem.model.Vehicle;
import tech.brito.parkingsystem.repository.VehicleRepository;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class ParkingService {

    private final VehicleRepository vehicleRepository;

    public ParkingService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public boolean CheckParkingAvailability() {
        return vehicleRepository.existParkingAvailability();
    }

    @Transactional
    public Vehicle checkIn(Vehicle vehicle) {
        vehicle.setEntryDate(OffsetDateTime.now());
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle checkOut(Vehicle vehicle) {
        vehicle.setExitDate(OffsetDateTime.now());
        vehicle.calculateBill();
        return vehicleRepository.save(vehicle);
    }
}
