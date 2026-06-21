package com.pharma.inventory_service.Service.Impl;

import com.pharma.inventory_service.DTO.Request.InventoryRequest;
import com.pharma.inventory_service.DTO.Response.InventoryResponse;
import com.pharma.inventory_service.Entity.Inventory;
import com.pharma.inventory_service.Exception.InsufficientStockException;
import com.pharma.inventory_service.Exception.InventoryNotFoundException;
import com.pharma.inventory_service.Repository.InventoryRepository;
import com.pharma.inventory_service.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;


    @Override
    public InventoryResponse createInventory(
            InventoryRequest request) {

        Inventory inventory = new Inventory();

        inventory.setMedicineId(
                request.getMedicineId());

        inventory.setTotalStock(
                request.getTotalStock());

        inventory.setReservedStock(0);

        inventory.setAvailableStock(
                request.getTotalStock());

        inventory.setReorderLevel(
                request.getReorderLevel());

        Inventory savedInventory =
                repository.save(inventory);

        return mapToResponse(savedInventory);
    }

    @Override
    public InventoryResponse getInventoryByMedicineId(
            Long medicineId) {

        Inventory inventory =
                repository.findByMedicineId(
                                medicineId)
                        .orElseThrow(() ->
                                new InventoryNotFoundException(
                                        "Inventory not found for medicine id: "
                                                + medicineId));

        return mapToResponse(inventory);
    }

    @Override
    public InventoryResponse addStock(
            Long medicineId,
            Integer quantity) {

        Inventory inventory =
                getInventoryEntity(medicineId);

        inventory.setTotalStock(
                inventory.getTotalStock() + quantity);

        inventory.setAvailableStock(
                inventory.getTotalStock()
                        - inventory.getReservedStock());

        Inventory updated =
                repository.save(inventory);

        return mapToResponse(updated);
    }

    @Override
    public InventoryResponse reduceStock(
            Long medicineId,
            Integer quantity) {

        Inventory inventory =
                getInventoryEntity(medicineId);

        if (quantity >
                inventory.getAvailableStock()) {

            throw new InsufficientStockException(
                    "Insufficient stock available");
        }

        inventory.setTotalStock(
                inventory.getTotalStock() - quantity);

        inventory.setAvailableStock(
                inventory.getTotalStock()
                        - inventory.getReservedStock());

        Inventory updated =
                repository.save(inventory);

        return mapToResponse(updated);
    }

    private Inventory getInventoryEntity(
            Long medicineId) {

        return repository.findByMedicineId(
                        medicineId)
                .orElseThrow(() ->
                        new InventoryNotFoundException(
                                "Inventory not found for medicine id: "
                                        + medicineId));
    }

    private InventoryResponse mapToResponse(
            Inventory inventory) {

        InventoryResponse response =
                new InventoryResponse();

        response.setId(inventory.getId());
        response.setMedicineId(
                inventory.getMedicineId());

        response.setTotalStock(
                inventory.getTotalStock());

        response.setReservedStock(
                inventory.getReservedStock());

        response.setAvailableStock(
                inventory.getAvailableStock());

        response.setReorderLevel(
                inventory.getReorderLevel());

        return response;
    }


    @Override
    public List<InventoryResponse>
    getLowStockMedicines() {

        return repository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getAvailableStock()
                                <= inventory.getReorderLevel())
                .map(this::mapToResponse)
                .toList();
    }

}
