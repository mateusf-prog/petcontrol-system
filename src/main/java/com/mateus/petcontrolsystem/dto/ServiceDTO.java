package com.mateus.petcontrolsystem.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ServiceDTO(
        Long id,

        @NotBlank(message = "Name cannot be null")
        @Size(min = 3, message = "Invalid name")
        String name,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Digits(integer=6, fraction=2, message = "Price must have up to 6 integer digits and 2 fraction digits")
        BigDecimal smallDogPrice,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Digits(integer=6, fraction=2, message = "Price must have up to 6 integer digits and 2 fraction digits")
        BigDecimal mediumSizeDogPrice,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Digits(integer=6, fraction=2, message = "Price must have up to 6 integer digits and 2 fraction digits")
        BigDecimal bigDogPrice
) {
}
