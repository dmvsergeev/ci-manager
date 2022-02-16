package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyEmailException;
import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorCustom implements UserValidator {
    @Override
    public void validate(@NotNull User user) throws CimanagerException {
        if (user.getEmail().isEmpty()) {
            throw new EmptyEmailException(user.getName() + " has empty email");
        }
    }
}
