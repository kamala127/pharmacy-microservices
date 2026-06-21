package com.pharma.inventory_service.DTO.Response;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,

        int status,

        String error,

        String message,

        String path
) {
}
