package com.pharma.inventory_service.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicineId;

    private Integer totalStock;

    private Integer reservedStock;

    private Integer availableStock;

    private Integer reorderLevel;


}
