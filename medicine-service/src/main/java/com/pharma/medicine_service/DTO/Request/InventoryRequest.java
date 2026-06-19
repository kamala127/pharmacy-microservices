package com.pharma.medicine_service.DTO.Request;

import lombok.Data;

@Data
public class InventoryRequest {
    private Integer quantityAvailable;

    private Integer reorderLevel;

    private String warehouseLocation;
}
