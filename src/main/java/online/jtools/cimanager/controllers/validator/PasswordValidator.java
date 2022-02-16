package online.jtools.cimanager.controllers.validator;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.pojo.Password;
import org.jetbrains.annotations.NotNull;

public interface PasswordValidator {
    void validate(@NotNull Password password) throws CimanagerException;
}
