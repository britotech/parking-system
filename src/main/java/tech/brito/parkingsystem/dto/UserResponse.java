package tech.brito.parkingsystem.dto;

import lombok.Data;
import tech.brito.parkingsystem.model.User;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String username;
    private String fullname;
    private String email;

    public UserResponse(User user) {
        id = user.getId();
        username = user.getUsername();
        fullname = user.getFullname();
        email = user.getEmail();
    }
}
