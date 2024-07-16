package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.enums.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record NewProductRequestDTO(
        @NotBlank(message = "Name cannot be null")
        @Size(min = 3, message = "Invalid name")
        String name,
        @NotBlank(message = "Supplier cannot be null")
        @Size(min = 3, message = "Invalid name")
        String supplier,
        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Digits(integer=6, fraction=2, message = "Price must have up to 6 integer digits and 2 fraction digits")
        BigDecimal price,
        String description,
        @NotNull(message = "Stock cannot be null")
        @DecimalMin(value = "0")
        Integer stock,
        @NotNull(message = "Category cannot be null")
        Category category
) {
}
