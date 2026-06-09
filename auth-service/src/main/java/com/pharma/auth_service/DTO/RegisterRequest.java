package com.pharma.auth_service.DTO;

public record RegisterRequest(String name,
                              String email,
                              String password,
                              Long roleId) {
}
