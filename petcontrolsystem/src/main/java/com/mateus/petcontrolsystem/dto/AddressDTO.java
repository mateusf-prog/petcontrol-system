package com.mateus.petcontrolsystem.dto;

public record AddressDTO (
    String zipCode,
    String street,
    String number,
    String district,
    String city,
    String uf
    ) {}

