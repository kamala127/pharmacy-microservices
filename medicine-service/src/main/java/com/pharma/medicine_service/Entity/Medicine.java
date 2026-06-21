package com.pharma.medicine_service.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    @NotNull
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotNull
    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    private LocalDate expiryDate;

    private LocalDateTime createdAt;


}
