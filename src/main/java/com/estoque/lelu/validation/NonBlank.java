package com.estoque.lelu.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NonBlankValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonBlank {
    String message() default "O campo não pode ser vazio ou conter apenas espaços em branco";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
