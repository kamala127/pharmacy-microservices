package com.pharma.order_service.Feign;

import com.pharma.order_service.DTO.Response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="inventory-service")
public interface InventoryClient {

    @GetMapping("/inventories/{medicineId}")
    InventoryResponse getInventory(
            @PathVariable Long medicineId);

    @PostMapping("/inventories/{medicineId}/reduce-stock")
    InventoryResponse reduceStock(
            @PathVariable Long medicineId,
            @RequestParam Integer quantity);
}
