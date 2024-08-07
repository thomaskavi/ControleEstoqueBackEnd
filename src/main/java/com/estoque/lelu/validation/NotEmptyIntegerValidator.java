package com.estoque.lelu.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyIntegerValidator implements ConstraintValidator<NotEmptyInteger, Integer> {
    @Override
    public void initialize(NotEmptyInteger constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value > 0; // Exemplo de validação
    }
}
