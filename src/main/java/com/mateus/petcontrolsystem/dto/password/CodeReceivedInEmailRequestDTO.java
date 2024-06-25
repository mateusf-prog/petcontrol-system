package com.mateus.petcontrolsystem.dto.password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeReceivedInEmailRequestDTO(

        String email, // this field will arrive implicitly
        @NotBlank(message = "Code cannot be null")
        @Size(min = 5, max = 5, message = "Code must be 5 numbers")
        String code) {}
