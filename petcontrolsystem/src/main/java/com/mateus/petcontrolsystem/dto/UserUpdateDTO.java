package com.mateus.petcontrolsystem.dto;

public record UserUpdateDTO (
        String name,
        String phone,
        String cpfCnpj,
        AddressDTO address
) {}
