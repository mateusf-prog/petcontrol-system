package com.mateus.petcontrolsystem.dto.validators.impl;

import com.mateus.petcontrolsystem.dto.validators.AtLeastTwelveYearsAgo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AtLeastTwelveYearsAgoValidator implements ConstraintValidator<AtLeastTwelveYearsAgo, LocalDate> {

    @Override
    public void initialize(AtLeastTwelveYearsAgo constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Period.between(value, LocalDate.now()).getYears() >= 12;
    }
}