package com.pharma.order_service.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull
    private Long medicineId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotBlank
    private String customerName;
}
