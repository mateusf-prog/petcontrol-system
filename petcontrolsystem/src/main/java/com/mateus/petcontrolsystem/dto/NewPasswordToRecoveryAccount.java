package com.mateus.petcontrolsystem.dto;

public record NewPasswordToRecoveryAccount(String email, String newPassword, String token) {}
