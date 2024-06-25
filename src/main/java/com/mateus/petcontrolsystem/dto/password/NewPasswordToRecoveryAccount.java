package com.mateus.petcontrolsystem.dto.password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record NewPasswordToRecoveryAccount(

        String email,
        @NotBlank(message = "Password cannot be null")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must have at least 8 characters, " +
                "one uppercase letter, one lowercase letter and one number. Ex: Password123")
        String newPassword) {}
