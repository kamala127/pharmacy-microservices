package com.pharma.medicine_service.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicineRequest(

        @NotBlank(message = "Medicine name is required")
        String medicineName,

        @NotBlank(message = "Manufacturer is required")
        String manufacturer,

        @NotBlank(message = "Category is required")
        String category,

        @NotNull
        @Positive
        BigDecimal price,

        InventoryRequest  inventory,

        @NotNull
        LocalDate expiryDate
) {
}
