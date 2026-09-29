package com.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // Opcional: "ADMIN" o "ALUMNO". Si no se manda, se crea como ALUMNO.
    private String rol;
}
