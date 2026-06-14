package com.pharma.auth_service.DTO;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password) {
}
