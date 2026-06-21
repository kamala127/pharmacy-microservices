package com.pharma.inventory_service.Service;

import com.pharma.inventory_service.DTO.Request.InventoryRequest;
import com.pharma.inventory_service.DTO.Response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse createInventory(
            InventoryRequest request);

    InventoryResponse getInventoryByMedicineId(
            Long medicineId);

    InventoryResponse addStock(
            Long medicineId,
            Integer quantity);

    InventoryResponse reduceStock(
            Long medicineId,
            Integer quantity);

    List<InventoryResponse>
    getLowStockMedicines();
}
