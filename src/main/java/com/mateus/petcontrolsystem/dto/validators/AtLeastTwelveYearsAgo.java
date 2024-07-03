package com.mateus.petcontrolsystem.dto.validators;

import com.mateus.petcontrolsystem.dto.validators.impl.AtLeastTwelveYearsAgoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AtLeastTwelveYearsAgoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastTwelveYearsAgo {
    String message() default "Date must be at least 12 years ago";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}