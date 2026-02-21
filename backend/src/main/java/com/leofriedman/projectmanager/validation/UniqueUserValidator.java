package com.leofriedman.projectmanager.validation;

import com.leofriedman.projectmanager.dto.UserRegistration;
import com.leofriedman.projectmanager.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, UserRegistration> {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean isValid(UserRegistration value, ConstraintValidatorContext context) {

        boolean valid = true;

        context.disableDefaultConstraintViolation();

        if (repository.findByUsername(value.getUsername()) != null) {
            context.buildConstraintViolationWithTemplate("Username already exists")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            valid = false;
        }

        if (repository.findByEmail(value.getEmail()) != null) {
            context.buildConstraintViolationWithTemplate("Email already exists")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}