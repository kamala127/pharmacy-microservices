package com.pharma.order_service.Service;

import com.pharma.order_service.DTO.Request.OrderRequest;
import com.pharma.order_service.DTO.Response.OrderResponse;
import com.pharma.order_service.Enum.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(
            OrderRequest request);

    OrderResponse getOrderById(
            Long id);

    List<OrderResponse> getAllOrders();

    OrderResponse updateStatus(
            Long id,
            OrderStatus status);
}
