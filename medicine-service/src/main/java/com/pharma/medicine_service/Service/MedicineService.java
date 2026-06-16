package com.pharma.medicine_service.Service;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.Entity.Medicine;

import java.util.List;

public interface MedicineService {

    MedicineResponse addMedicine(
            MedicineRequest request);

    MedicineResponse getMedicineById(Long id);

    List<MedicineResponse> getAllMedicines();

    MedicineResponse updateMedicine(
            Long id,
            MedicineRequest request);

    void deleteMedicine(Long id);



}
