package com.timo.moosmann.tbr.mybank.web.validation.user_not_exists;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNotExistsValidator.class)
public @interface UserNotExists {
    String message() default "User was already created";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
