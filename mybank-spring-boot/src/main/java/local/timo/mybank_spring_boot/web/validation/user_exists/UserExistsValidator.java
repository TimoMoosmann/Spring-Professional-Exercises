package local.timo.mybank_spring_boot.web.validation.user_exists;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import local.timo.mybank_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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
