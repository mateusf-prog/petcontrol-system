package com.mateus.petcontrolsystem.dto.password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

public record CodeReceivedInEmailRequestDTO(

        @NotBlank
        @Email
        String email, // this field will arrive implicitly
        @NotBlank(message = "Code cannot be null")
        @Pattern(regexp = "\\d+", message = "The field must contain only numbers.")
        @Size(min = 5, max = 5, message = "Code must be 5 numbers")
        String code) {}
