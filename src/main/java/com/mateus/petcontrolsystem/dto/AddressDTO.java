package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.utils.ZipCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AddressDTO (

    @NotBlank(message = "Zip Code cannot be null")
    @ZipCode
    String zipCode,
    @NotBlank(message = "Street cannot be null")
    @Size(min = 7, message = "Invalid street")
    String street,
    @NotBlank(message = "House number cannot be null")
    @Positive(message = "Invalid house number")
    String number,
    @NotBlank(message = "District cannot be null")
    @Size(min = 5, message = "Invalid district")
    String district,
    @NotBlank(message = "City cannot be null")
    @Size(min = 3, message = "Invalid city")
    String city,
    @NotBlank(message = "UF cannot be null")
    @Size(min = 2, max = 2, message = "Invalid UF")
    String uf
    ) {}

