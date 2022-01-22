package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyEmailException;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.Password;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidatorCustom implements PasswordValidator {
    @Override
    public void validate(Password password) throws ValidationException {
        if (password.getPassword().isEmpty()) {
            throw new EmptyEmailException(password.getPassword() + " has empty password");
        }
    }
}
