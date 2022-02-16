package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.EmptyFieldException;
import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppValidatorCustom implements AppValidator {
    @Override
    public void validate(@NotNull App app) throws CimanagerException {
        if (isEmpty(app.getName())) {
            throw new EmptyFieldException(app.getName() + " has empty name");
        }

        if (isEmpty(app.getUrl())) {
            throw new EmptyFieldException(app.getName() + " has empty url");
        }
    }

    private boolean isEmpty(@Nullable String value) {
        return value == null || value.isEmpty();
    }
}
