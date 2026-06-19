package com.pharma.medicine_service.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name="inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantityAvailable;

    private Integer reorderLevel;

    private String warehouseLocation;

    private LocalDateTime lastUpdated;
}
