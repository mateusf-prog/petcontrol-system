package com.mateus.petcontrolsystem.dto;

import java.time.LocalDate;

public record RegisterRequestDTO(
        String name,
        String email,
        String cpfCnpj,
        String phone,
        LocalDate birthDate,
        String password,
        AddressDTO address
) {}
