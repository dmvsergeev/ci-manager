package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyEmailException;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.App;
import org.springframework.stereotype.Component;

@Component
public class AppValidatorCustom implements AppValidator {
    @Override
    public void validate(App app) throws ValidationException {
        if (app.getName() == null || app.getName().isEmpty()) {
            throw new EmptyEmailException(app.getName() + " has empty name");
        }

        if (app.getUrl() == null || app.getUrl().isEmpty()) {
            throw new EmptyEmailException(app.getName() + " has empty url");
        }
    }
}
