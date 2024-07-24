package com.timo.moosmann.tbr.mybank.web.validation.user_not_exists;

import com.timo.moosmann.tbr.mybank.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserNotExistsValidator implements ConstraintValidator<UserNotExists, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserNotExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext constraintValidatorContext) {
        if (userId == null) {
            return false;
        }

        return !userService.doesExist(userId);
    }
}
