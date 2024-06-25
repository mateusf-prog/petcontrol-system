package com.mateus.petcontrolsystem.dto.password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailToRecoverPasswordDTO(

        @NotBlank(message = "Email cannot be null")
        @Email(message = "Invalid email")
        String email) {}
