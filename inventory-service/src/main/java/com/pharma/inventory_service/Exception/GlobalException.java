package com.pharma.inventory_service.Exception;

import com.pharma.inventory_service.DTO.Response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryNotFound(InventoryNotFoundException ex,
                                                                 HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(

                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Inventory Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(InsufficientStockException ex,
                                                                 HttpServletRequest request){

        ErrorResponse errorResponse = new ErrorResponse(

                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Insufficient Stock",
                ex.getMessage(),
                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);


    }
}
