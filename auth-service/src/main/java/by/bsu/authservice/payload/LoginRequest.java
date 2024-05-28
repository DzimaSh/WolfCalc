package by.bsu.authservice.payload;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
