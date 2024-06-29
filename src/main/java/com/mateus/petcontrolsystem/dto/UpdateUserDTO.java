package com.mateus.petcontrolsystem.dto;

// The cpfCnpj comes in the request only for database query purposes

import com.mateus.petcontrolsystem.dto.validators.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(

        @NotBlank(message = "Name cannot be null")
        @Size(min = 5, message = "Invalid name")
        String name,
        @NotBlank(message = "Phone number cannot be null")
        @Phone
        String phone,
        String cpfCnpj, // this field not come changed
        @Valid
        AddressDTO address
) {}
