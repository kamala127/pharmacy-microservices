package com.pharma.order_service.Controller;

import com.pharma.order_service.DTO.Request.OrderRequest;
import com.pharma.order_service.DTO.Response.OrderResponse;
import com.pharma.order_service.Enum.OrderStatus;
import com.pharma.order_service.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse>
    createOrder(
            @Valid
            @RequestBody OrderRequest request) {

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(service.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse>
    getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>>
    getAllOrders() {

        return ResponseEntity.ok(
                service.getAllOrders());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse>
    updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return ResponseEntity.ok(
                service.updateStatus(id, status));
    }

}
