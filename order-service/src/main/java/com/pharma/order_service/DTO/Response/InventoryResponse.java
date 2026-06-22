package com.pharma.order_service.DTO.Response;

import lombok.Data;

@Data
public class InventoryResponse {
    private Long id;

    private Long medicineId;

    private Integer totalStock;

    private Integer reservedStock;

    private Integer availableStock;

    private Integer reorderLevel;
}
