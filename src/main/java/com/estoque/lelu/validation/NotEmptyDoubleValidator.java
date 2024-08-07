package com.estoque.lelu.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyDoubleValidator implements ConstraintValidator<NotEmptyDouble, Double> {
    @Override
    public void initialize(NotEmptyDouble constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value > 0; // Exemplo de validação
    }
}
