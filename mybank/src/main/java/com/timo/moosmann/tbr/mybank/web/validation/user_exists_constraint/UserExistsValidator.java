package com.timo.moosmann.tbr.mybank.web.validation.user_exists_constraint;

import com.timo.moosmann.tbr.mybank.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public class UserExistsValidator implements ConstraintValidator<UserExists, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext constraintValidatorContext) {
        if (userId == null) {
            return false;
        }

        return userService.doesExist(userId);
    }
}
