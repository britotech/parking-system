package tech.brito.parkingsystem.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.brito.parkingsystem.core.configs.security.AuthenticationCurrentUserService;
import tech.brito.parkingsystem.dto.PasswordUpdateDTO;
import tech.brito.parkingsystem.dto.UserResponse;
import tech.brito.parkingsystem.exception.DomainRuleException;
import tech.brito.parkingsystem.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final AuthenticationCurrentUserService currentUserService;

    public UserController(UserService userService, AuthenticationCurrentUserService currentUserService) {
        this.userService = userService;
        this.currentUserService = currentUserService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        var users = userService.findAll();
        return users.stream().map(u -> new UserResponse(u)).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable UUID userId) {
        currentUserService.validatePermission(userId);
        var user = userService.findByIdOrThrow(userId);
        return new UserResponse(user);
    }

    @PutMapping("/{userId}/password")
    public String updatePassword(@PathVariable UUID userId, @RequestBody @Valid PasswordUpdateDTO passwordDTO) {
        var userSaved = userService.findByIdOrThrow(userId);
        if (!userSaved.getPassword().equals(passwordDTO.getOldPassword())) {
            throw new DomainRuleException("Old password does not match user's current password");
        }

        userSaved.setPassword(passwordDTO.getPassword());
        userService.save(userSaved);
        return "Password updated sucessfully";
    }
}
