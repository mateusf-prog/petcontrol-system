package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.dto.validators.AtLeastTwelveYearsAgo;
import com.mateus.petcontrolsystem.dto.validators.CpfCnpj;
import com.mateus.petcontrolsystem.dto.validators.Phone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterRequestDTO(

        @NotBlank(message = "Name cannot be null")
        @Size(min = 5, message = "Invalid name")
        String name,
        @NotBlank(message = "Email cannot be null")
        @Email(message = "Invalid Email")
        String email,
        @NotBlank(message = "CPF/CNPJ cannot be null")
        @CpfCnpj
        String cpfCnpj,
        @NotBlank(message = "Phone number cannot be null")
        @Phone
        String phone,
        @NotNull
        @AtLeastTwelveYearsAgo
        LocalDate birthDate,
        @NotBlank(message = "Password cannot be null")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must have at least 8 characters, " +
                "one uppercase letter, one lowercase letter and one number. Ex: Password123")
        String password,
        @Valid
        AddressDTO address
) {}
