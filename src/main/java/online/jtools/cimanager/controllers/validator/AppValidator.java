package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;

public interface AppValidator {
    void validate(@NotNull App app) throws CimanagerException;
}
