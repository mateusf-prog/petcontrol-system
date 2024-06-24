package com.mateus.petcontrolsystem.dto;

public record UpdateUserDTO(
        String name,
        String phone,
        String cpfCnpj,
        AddressDTO address
) {}
