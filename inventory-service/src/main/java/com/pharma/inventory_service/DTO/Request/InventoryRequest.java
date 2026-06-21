package com.pharma.inventory_service.DTO.Request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InventoryRequest {

    @NotNull
    private Long medicineId;

    @PositiveOrZero
    private Integer totalStock;

    @PositiveOrZero
    private Integer reorderLevel;
}
