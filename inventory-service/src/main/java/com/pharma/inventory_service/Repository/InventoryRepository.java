package com.pharma.inventory_service.Repository;

import com.pharma.inventory_service.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory>
    findByMedicineId(Long medicineId);


    List<Inventory>
    findByAvailableStockLessThanEqual(
            Integer reorderLevel);
}
