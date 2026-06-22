package com.pharma.order_service.Service.Impl;

import com.pharma.order_service.DTO.Request.OrderRequest;
import com.pharma.order_service.DTO.Response.InventoryResponse;
import com.pharma.order_service.DTO.Response.OrderResponse;
import com.pharma.order_service.Entity.Order;
import com.pharma.order_service.Enum.OrderStatus;
import com.pharma.order_service.Exception.InsufficientStockException;
import com.pharma.order_service.Exception.OrderNotFoundException;
import com.pharma.order_service.Feign.InventoryClient;
import com.pharma.order_service.Repository.OrderRepository;
import com.pharma.order_service.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final InventoryClient inventoryClient;

    @Retry(name = "inventoryRetry")
    @CircuitBreaker(
            name = "inventory-service",
            fallbackMethod = "inventoryFallback")
    @Override
    public OrderResponse createOrder(
            OrderRequest request) {

        System.out.println("Calling Inventory Service...");
        InventoryResponse inventory =
                inventoryClient.getInventory(
                        request.getMedicineId());

        if (inventory.getAvailableStock()
                < request.getQuantity()) {

            throw new InsufficientStockException(
                    "Insufficient stock available");
        }

        inventoryClient.reduceStock(
                request.getMedicineId(),
                request.getQuantity());

        Order order = Order.builder()
                .medicineId(request.getMedicineId())
                .quantity(request.getQuantity())
                .customerName(request.getCustomerName())
                .status(OrderStatus.CREATED)
                .build();

        return mapToResponse(
                repository.save(order));
    }

    @Override
    public OrderResponse getOrderById(
            Long id) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found"));

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrderResponse updateStatus(
            Long id,
            OrderStatus status) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found"));

        order.setStatus(status);

        return mapToResponse(
                repository.save(order));
    }

    private OrderResponse mapToResponse(
            Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .medicineId(order.getMedicineId())
                .quantity(order.getQuantity())
                .customerName(order.getCustomerName())
                .status(order.getStatus())
                .build();
    }

    private OrderResponse inventoryFallback(
            OrderRequest request,
            Exception ex) {

        System.out.println(
                "Fallback executed : "
                        + ex.getClass().getSimpleName());

        return OrderResponse.builder()
                .id(-1L)
                .medicineId(request.getMedicineId())
                .quantity(request.getQuantity())
                .customerName(request.getCustomerName())
                .status(OrderStatus.CREATED)
                .build();
    }

}
