package com.estoque.lelu.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NonBlankValidator implements ConstraintValidator<NonBlank, String> {

    @Override
    public void initialize(NonBlank constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.trim().isEmpty();
    }
}
