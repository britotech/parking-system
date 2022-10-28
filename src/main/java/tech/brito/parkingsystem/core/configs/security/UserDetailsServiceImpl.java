package tech.brito.parkingsystem.core.configs.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.brito.parkingsystem.repository.UserRepository;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));

        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserById(UUID userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(String.format("User not found with id: %s", userId)));

        return UserDetailsImpl.build(user);
    }
}
