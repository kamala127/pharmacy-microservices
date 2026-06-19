package com.pharma.medicine_service.Controller;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.DTO.Response.PageResponse;
import com.pharma.medicine_service.Service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineResponse> addMedicine(
            @Valid
            @RequestBody MedicineRequest medicineRequest){

       MedicineResponse response =  medicineService.addMedicine(medicineRequest);
       return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER','PHARMACY')")
    public ResponseEntity<MedicineResponse>
    getMedicineById(@PathVariable Long id) {

        return ResponseEntity.ok(
                medicineService.getMedicineById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER','PHARMACY')")
    public ResponseEntity<List<MedicineResponse>>
    getAllMedicines() {

        return ResponseEntity.ok(
                medicineService.getAllMedicines());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineResponse>
    updateMedicine(
            @PathVariable Long id,
            @Valid
            @RequestBody MedicineRequest request) {

        return ResponseEntity.ok(
                medicineService.updateMedicine(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>
    deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER','PHARMACY')")
    public ResponseEntity<PageResponse<MedicineResponse>>
    getAllMedicines(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "medicineName")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return ResponseEntity.ok(
                medicineService.getAllMedicines(
                        page,
                        size,
                        sortBy,
                        direction));
    }


    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER','PHARMACY')")
    public ResponseEntity<List<MedicineResponse>>
    searchMedicine(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                medicineService.searchMedicine(keyword));
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER','PHARMACY')")
    public ResponseEntity<List<MedicineResponse>>
    getByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                medicineService.getMedicinesByCategory(
                        category));
    }
    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<List<MedicineResponse>>
    getByManufacturer(
            @PathVariable String manufacturer) {

        return ResponseEntity.ok(
                medicineService.getMedicinesByManufacturer(
                        manufacturer));
    }

    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {

        if (authentication == null) {
            return "Authentication is NULL";
        }

        return authentication.getName()
                + " -> "
                + authentication.getAuthorities();
    }
}
