package com.mateus.petcontrolsystem.dto.validators;

import com.mateus.petcontrolsystem.dto.validators.impl.CpfCnpjValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfCnpjValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {

    String message() default "Invalid CPF or CNPJ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}