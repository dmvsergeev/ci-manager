package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;

public interface UserValidator {
    void validate(@NotNull User user) throws CimanagerException;
}
