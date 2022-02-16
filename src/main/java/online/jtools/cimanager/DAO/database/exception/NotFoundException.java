package online.jtools.cimanager.DAO.database.exception;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;

public class NotFoundException extends CimanagerException {
    public NotFoundException(String message) {
        super("Not found", message);
    }
}
