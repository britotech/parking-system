package tech.brito.parkingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.brito.parkingsystem.enums.RoleType;
import tech.brito.parkingsystem.model.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleType name);
}
