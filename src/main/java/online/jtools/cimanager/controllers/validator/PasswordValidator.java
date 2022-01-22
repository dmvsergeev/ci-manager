package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.Password;

public interface PasswordValidator {
    void validate(Password password) throws ValidationException;
}
