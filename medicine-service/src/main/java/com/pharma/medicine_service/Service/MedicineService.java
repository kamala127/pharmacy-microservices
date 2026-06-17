package com.pharma.medicine_service.Service;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.DTO.Response.PageResponse;


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

    PageResponse<MedicineResponse> getAllMedicines(
            int page,
            int size,
            String sortBy,
            String direction);

    List<MedicineResponse> searchMedicine(
            String keyword);

    List<MedicineResponse> getMedicinesByCategory(
            String category);

    List<MedicineResponse> getMedicinesByManufacturer(
            String manufacturer);



}
