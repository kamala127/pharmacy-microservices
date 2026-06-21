package com.pharma.medicine_service.Service;

import com.pharma.medicine_service.DTO.Request.MedicineRequest;
import com.pharma.medicine_service.DTO.Response.MedicineResponse;
import com.pharma.medicine_service.Entity.Medicine;
import com.pharma.medicine_service.Exception.MedicineNotFoundException;
import com.pharma.medicine_service.Repository.MedicineRepository;
import com.pharma.medicine_service.Service.Impl.MedicineServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceImplTest {

    @Mock
    private MedicineRepository repository;

    @InjectMocks
    private MedicineServiceImpl service;

    @Test
    void shouldReturnMedicineWhenIdExists() {

        Medicine medicine = new Medicine();
        medicine.setId(1L);
        medicine.setMedicineName("Paracetamol");

        when(repository.findById(1L))
                .thenReturn(Optional.of(medicine));

        MedicineResponse response =
                service.getMedicineById(1L);

        assertEquals(
                "Paracetamol",
                response.medicineName());

        verify(repository)
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenMedicineNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                MedicineNotFoundException.class,
                () -> service.getMedicineById(1L));
    }

}
