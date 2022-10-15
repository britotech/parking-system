package tech.brito.parkingsystem.repository;

import org.springframework.beans.factory.annotation.Value;
import tech.brito.parkingsystem.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VehicleRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    @Value(value = "${spaces-for-vehicles}")
    private Integer spacesForVehicles;

    public boolean existParkingAvailability() {
        var sql = """
                    SELECT COUNT(v)
                    FROM Vehicle v
                    WHERE v.exitDate is null              
                """;

        var parkedVehicles = (Long) manager.createQuery(sql).getSingleResult();
        return spacesForVehicles > parkedVehicles;
    }

    public List<Vehicle> findAllParkedVehicles() {
        var sql = """
                    SELECT v
                    FROM Vehicle v
                    WHERE v.exitDate is null
                """;

        return manager.createQuery(sql).getResultList();
    }
}
