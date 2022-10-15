package tech.brito.parkingsystem.service;

import org.springframework.stereotype.Service;
import tech.brito.parkingsystem.exception.VehicleNotFoundException;
import tech.brito.parkingsystem.model.Vehicle;
import tech.brito.parkingsystem.repository.VehicleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllParkedVehicles() {
        return vehicleRepository.findAllParkedVehicles();
    }

    public Vehicle findByIdOrThrow(UUID id) {
        return vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);
    }
}
