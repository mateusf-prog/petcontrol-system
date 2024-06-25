package com.mateus.petcontrolsystem.utils;

import com.mateus.petcontrolsystem.utils.impl.ZipCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {

    String message() default "Invalid zip code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}