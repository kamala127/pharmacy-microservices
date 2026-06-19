package com.pharma.medicine_service.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    private Long id;

    private Integer quantityAvailable;

    private Integer reorderLevel;

    private String warehouseLocation;

    private LocalDateTime lastUpdated;
}
