package tech.brito.parkingsystem.dto;

import lombok.Data;
import tech.brito.parkingsystem.core.validations.Username;
import tech.brito.parkingsystem.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @Username
    private String username;

    @NotBlank
    @Size(max = 150)
    private String fullname;

    @NotBlank
    @Email
    @Size(max = 80)
    private String email;

    @NotBlank
    @Size(min = 3, max = 255)
    private String password;

    public User toEntity() {
        var user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
