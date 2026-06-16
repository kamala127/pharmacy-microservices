package com.pharma.medicine_service.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicineResponse(

        Long id,
        String medicineName,
        String manufacturer,
        String category,
        BigDecimal price,
        Integer stockQuantity,
        LocalDate expiryDate
) {
}
