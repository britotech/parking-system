package tech.brito.parkingsystem.service;

import org.springframework.stereotype.Service;
import tech.brito.parkingsystem.enums.RoleType;
import tech.brito.parkingsystem.enums.UserStatus;
import tech.brito.parkingsystem.exception.DomainRuleException;
import tech.brito.parkingsystem.exception.UserNotFoundException;
import tech.brito.parkingsystem.model.User;
import tech.brito.parkingsystem.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByIdOrThrow(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User createUser(User user) {
        validateUser(user);
        user.setStatus(UserStatus.ACTIVE);
        addRoleToUser(user, RoleType.ROLE_USER);

        return save(user);
    }

    private void validateUser(User user) {
        validateEmailAllowed(user);
        validateUsernameAllowed(user);
    }

    private void validateEmailAllowed(User user) {
        var optionalSavedUser = userRepository.findByEmail(user.getEmail());

        if (optionalSavedUser.isPresent() && !optionalSavedUser.get().equals(user)) {
            throw new DomainRuleException(String.format("There is already a registered user with the email %s", user.getEmail()));
        }
    }

    private void validateUsernameAllowed(User user) {
        var optionalSavedUser = userRepository.findByUsername(user.getUsername());

        if (optionalSavedUser.isPresent() && !optionalSavedUser.get().equals(user)) {
            throw new DomainRuleException(String.format("There is already a registered user with the username %s", user.getEmail()));
        }
    }

    private void addRoleToUser(User user, RoleType roleType) {
        var role = roleService.findByNameOrThrow(roleType);
        user.getRoles().add(role);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
