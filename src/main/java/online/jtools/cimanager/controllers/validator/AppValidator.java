package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.App;

public interface AppValidator {
    void validate(App app) throws ValidationException;
}
