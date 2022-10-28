package tech.brito.parkingsystem.service;

import org.springframework.stereotype.Service;
import tech.brito.parkingsystem.enums.RoleType;
import tech.brito.parkingsystem.exception.RoleNotFoundException;
import tech.brito.parkingsystem.model.Role;
import tech.brito.parkingsystem.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByNameOrThrow(RoleType name) {
        return roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
    }
}
