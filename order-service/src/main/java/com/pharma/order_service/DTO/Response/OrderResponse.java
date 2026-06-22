package com.pharma.order_service.DTO.Response;

import com.pharma.order_service.Enum.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    private Long id;

    private Long medicineId;

    private Integer quantity;

    private String customerName;

    private OrderStatus status;
}
