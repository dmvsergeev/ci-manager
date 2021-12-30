package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.User;

public interface UserValidator {
    void validate(User user) throws ValidationException;
}
