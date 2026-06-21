package com.pharma.inventory_service.Exception;

public class InventoryNotFoundException extends RuntimeException{

    public InventoryNotFoundException(
            String message) {
        super(message);
    }
}
