package tech.brito.parkingsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String password;

    @NotBlank
    @Size(min = 3, max = 255)
    private String oldPassword;
}
