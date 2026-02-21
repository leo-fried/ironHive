package com.leofriedman.projectmanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValidator.class)
@Documented
public @interface UniqueUser {

    String message() default "Username or Email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}