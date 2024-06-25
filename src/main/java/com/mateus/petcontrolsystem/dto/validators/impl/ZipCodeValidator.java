package com.mateus.petcontrolsystem.dto.validators.impl;

import com.mateus.petcontrolsystem.dto.validators.ZipCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    @Override
    public void initialize(ZipCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String digitsOnly = value.replace("-", "");
        return digitsOnly.matches("\\d{8}");
    }
}