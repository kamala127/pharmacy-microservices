package com.pharma.medicine_service.Repository;

import com.pharma.medicine_service.Entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {


    Optional<Medicine> findByMedicineName(String medicineName);

    List<Medicine> findByCategory(String category);

    List<Medicine> findByManufacturer(String manufacturer);
}
