package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyEmailException;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorCustom implements UserValidator {
    @Override
    public void validate(User user) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new EmptyEmailException(user.getName() + " has empty email");
        }
    }
}
