package com.pharma.medicine_service.Controller;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.Service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<MedicineResponse> addMedicine(
            @Valid
            @RequestBody MedicineRequest medicineRequest){

       MedicineResponse response =  medicineService.addMedicine(medicineRequest);
       return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponse>
    getMedicineById(@PathVariable Long id) {

        return ResponseEntity.ok(
                medicineService.getMedicineById(id));
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponse>>
    getAllMedicines() {

        return ResponseEntity.ok(
                medicineService.getAllMedicines());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponse>
    updateMedicine(
            @PathVariable Long id,
            @Valid
            @RequestBody MedicineRequest request) {

        return ResponseEntity.ok(
                medicineService.updateMedicine(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return ResponseEntity.noContent().build();
    }
}
