package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.utils.CpfCnpj;
import com.mateus.petcontrolsystem.utils.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

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
        // todo: validate this fields
        LocalDate birthDate,
        String password,
        AddressDTO address
) {}
