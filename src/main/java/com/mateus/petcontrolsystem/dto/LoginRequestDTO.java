package com.mateus.petcontrolsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO(

        @Email(message = "Invalid Email")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must have at least 8 characters, " +
                "one uppercase letter, one lowercase letter and one number. Ex: Password123")
        String password)
{}

