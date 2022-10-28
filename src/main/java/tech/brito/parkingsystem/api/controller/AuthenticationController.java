package tech.brito.parkingsystem.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.brito.parkingsystem.core.configs.security.JwtProvider;
import tech.brito.parkingsystem.dto.LoginRequest;
import tech.brito.parkingsystem.dto.LoginResponse;
import tech.brito.parkingsystem.dto.SignUpRequest;
import tech.brito.parkingsystem.dto.UserResponse;
import tech.brito.parkingsystem.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthenticationController(UserService userService,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,
                                    JwtProvider jwtProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        var user = userService.createUser(signUpRequest.toEntity());
        return new UserResponse(user);
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        var userAuthentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(userAuthentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwtToken = jwtProvider.generateJwt(authentication);
        return new LoginResponse(jwtToken);
    }
}
