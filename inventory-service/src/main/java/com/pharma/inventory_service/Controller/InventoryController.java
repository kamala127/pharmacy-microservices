package com.pharma.inventory_service.Controller;

import com.pharma.inventory_service.DTO.Request.InventoryRequest;
import com.pharma.inventory_service.DTO.Response.InventoryResponse;
import com.pharma.inventory_service.Service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {


        private final InventoryService service;

        @PostMapping
        public ResponseEntity<InventoryResponse>
        createInventory(
                @Valid
                @RequestBody InventoryRequest request) {

            return ResponseEntity.status(
                            HttpStatus.CREATED)
                    .body(
                            service.createInventory(
                                    request));
        }

        @GetMapping("/{medicineId}")
        public ResponseEntity<InventoryResponse>
        getInventory(
                @PathVariable Long medicineId) {

            return ResponseEntity.ok(
                    service.getInventoryByMedicineId(
                            medicineId));
        }

        @PostMapping("/{medicineId}/add-stock")
        public ResponseEntity<InventoryResponse>
        addStock(
                @PathVariable Long medicineId,
                @RequestParam Integer quantity) {

            return ResponseEntity.ok(
                    service.addStock(
                            medicineId,
                            quantity));
        }

        @PostMapping("/{medicineId}/reduce-stock")
        public ResponseEntity<InventoryResponse>
        reduceStock(
                @PathVariable Long medicineId,
                @RequestParam Integer quantity) {

            return ResponseEntity.ok(
                    service.reduceStock(
                            medicineId,
                            quantity));
        }

        @GetMapping("/low-stock")
        public ResponseEntity<List<InventoryResponse>>
        getLowStockMedicines() {

            return ResponseEntity.ok(
                    service.getLowStockMedicines());
        }
}
