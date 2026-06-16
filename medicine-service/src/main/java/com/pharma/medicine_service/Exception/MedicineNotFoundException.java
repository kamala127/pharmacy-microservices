package com.pharma.medicine_service.Exception;

public class MedicineNotFoundException extends RuntimeException{

    public MedicineNotFoundException(
            String message) {
        super(message);
    }
}
