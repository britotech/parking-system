package tech.brito.parkingsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {

    @NonNull
    private String token;

    private String type = "Bearer";

    @Override
    public String toString() {
        return String.format("%s %s", type, token);
    }
}
