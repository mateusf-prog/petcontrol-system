package com.mateus.petcontrolsystem.dto;

import java.time.LocalDate;

public record GetUserDataResponseDTO(
        String name,
        String email,
        String cpfCnpj,
        String phone,
        LocalDate birthDate,
        AddressDTO address
) {
}
