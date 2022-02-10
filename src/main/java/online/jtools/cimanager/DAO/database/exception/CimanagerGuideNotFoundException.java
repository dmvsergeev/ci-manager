package online.jtools.cimanager.DAO.database.exception;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;

public class CimanagerGuideNotFoundException extends ValidationException {
    public CimanagerGuideNotFoundException(String message) {
        super("Not found", message);
    }
}
