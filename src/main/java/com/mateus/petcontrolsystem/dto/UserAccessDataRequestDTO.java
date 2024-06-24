package com.mateus.petcontrolsystem.dto;

public record UserAccessDataRequestDTO (String email, String actualPassword, String newPassword) {}
