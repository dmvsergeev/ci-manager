package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyFieldException;
import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.Password;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidatorCustom implements PasswordValidator {
    @Override
    public void validate(@NotNull Password password) throws CimanagerException {
        if (password.getPassword().isEmpty()) {
            throw new EmptyFieldException(password.getPassword() + "has empty password");
        }
    }
}
