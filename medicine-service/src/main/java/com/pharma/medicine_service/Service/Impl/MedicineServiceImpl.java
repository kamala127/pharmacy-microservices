package com.pharma.medicine_service.Service.Impl;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.InventoryResponse;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.DTO.Response.PageResponse;
import com.pharma.medicine_service.Entity.Inventory;
import com.pharma.medicine_service.Entity.Medicine;
import com.pharma.medicine_service.Exception.MedicineNotFoundException;
import com.pharma.medicine_service.Repository.MedicineRepository;
import com.pharma.medicine_service.Service.MedicineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository repository;

    // Add Medicine
    @Override
    public MedicineResponse addMedicine(
            MedicineRequest request) {

        Inventory inventory =
                Inventory.builder()
                        .quantityAvailable(
                                request.inventory()
                                        .getQuantityAvailable())
                        .reorderLevel(
                                request.inventory()
                                        .getReorderLevel())
                        .warehouseLocation(
                                request.inventory()
                                        .getWarehouseLocation())
                        .lastUpdated(
                                LocalDateTime.now())
                        .build();
        Medicine medicine =
                Medicine.builder()
                        .medicineName(
                                request.medicineName())
                        .manufacturer(
                                request.manufacturer())
                        .category(
                                request.category())
                        .price(request.price())
                        .inventory(
                                inventory)
                        .expiryDate(
                                request.expiryDate())
                        .createdAt(
                                LocalDateTime.now())
                        .build();
        log.info("Medicine Added");
        Medicine saved =
                repository.save(medicine);

        return mapToResponse(saved);
    }

    //getMedicineById

    @Override
    public MedicineResponse getMedicineById(
            Long id) {
        log.info("Fetching medicine with id: {}", id);
        Medicine medicine =
                repository.findById(id)
                        .orElseThrow(() ->{
                            log.error("Medicine not found with id: {}", id);
                            return new MedicineNotFoundException(
                                    "Medicine not found with id: " + id);
                        });
        log.info("Medicine fetched successfully with id: {}", id);

        return mapToResponse(medicine);
    }

    @Override
    public List<MedicineResponse>
    getAllMedicines() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MedicineResponse updateMedicine(
            Long id,
            MedicineRequest request) {





        Medicine medicine =
                repository.findById(id)
                        .orElseThrow(() ->
                                new MedicineNotFoundException(
                                        "Medicine not found"));

        medicine.setMedicineName(
                request.medicineName());

        medicine.setManufacturer(
                request.manufacturer());

        medicine.setCategory(
                request.category());

        medicine.setPrice(
                request.price());

        medicine.setExpiryDate(
                request.expiryDate());

        Inventory inventory =
                medicine.getInventory();

        inventory.setQuantityAvailable(
                request.inventory()
                        .getQuantityAvailable());

        inventory.setReorderLevel(
                request.inventory()
                        .getReorderLevel());

        inventory.setWarehouseLocation(
                request.inventory()
                        .getWarehouseLocation());

        inventory.setLastUpdated(
                LocalDateTime.now());

        Medicine updated =
                repository.save(medicine);

        return mapToResponse(updated);
    }

    @Override
    public void deleteMedicine(Long id) {

        log.info("fetching medicine with id: {}", id);
        Medicine medicine =
                repository.findById(id)
                        .orElseThrow(() -> {
                            log.error("medicine not found with id: {}", id);
                           return new MedicineNotFoundException(
                                    "Medicine not found");
                        });
        log.info("Medicine Deleted successfully with id: {}", id);
        repository.delete(medicine);
    }


    private MedicineResponse
    mapToResponse(Medicine medicine) {

        InventoryResponse inventoryResponse =
                new InventoryResponse(
                        medicine.getInventory().getId(),
                        medicine.getInventory()
                                .getQuantityAvailable(),
                        medicine.getInventory()
                                .getReorderLevel(),
                        medicine.getInventory()
                                .getWarehouseLocation(),
                        medicine.getInventory()
                                .getLastUpdated()
                );

        return new MedicineResponse(
                        medicine.getId(),
                        medicine.getMedicineName(),
                        medicine.getManufacturer(),
                        medicine.getCategory(),
                        medicine.getPrice(),
                inventoryResponse,
                medicine.getExpiryDate()
                );
    }

    @Override
    public PageResponse<MedicineResponse> getAllMedicines(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort =
                direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Medicine> medicinePage =
                repository.findAll(pageable);

        List<MedicineResponse> medicines =
                medicinePage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return PageResponse.<MedicineResponse>builder()
                .content(medicines)
                .currentPage(medicinePage.getNumber())
                .totalPages(medicinePage.getTotalPages())
                .totalElements(medicinePage.getTotalElements())
                .pageSize(medicinePage.getSize())
                .first(medicinePage.isFirst())
                .last(medicinePage.isLast())
                .build();
    }

    @Override
    public List<MedicineResponse> searchMedicine(
            String keyword) {

        return repository
                .findByMedicineNameContainingIgnoreCase(
                        keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<MedicineResponse> getMedicinesByCategory(
            String category) {

        return repository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MedicineResponse> getMedicinesByManufacturer(
            String manufacturer) {

        return repository.findByManufacturer(
                        manufacturer)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

}
