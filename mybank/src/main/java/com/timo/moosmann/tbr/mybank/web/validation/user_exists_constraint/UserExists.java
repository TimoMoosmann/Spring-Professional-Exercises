package com.timo.moosmann.tbr.mybank.web.validation.user_exists_constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserExistsValidator.class)
public @interface UserExists {
    String message() default "{UserExists constraint failed.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
