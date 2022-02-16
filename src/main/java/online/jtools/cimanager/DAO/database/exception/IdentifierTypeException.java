package online.jtools.cimanager.DAO.database.exception;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;

public class IdentifierTypeException extends CimanagerException {
    public IdentifierTypeException(String message) {
        super("Identifier type", message);
    }
}
