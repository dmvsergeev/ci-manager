package online.jtools.cimanager.DAO.database.exception;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;

public class CimanagerGuideDBDataCorruption extends ValidationException {
    public CimanagerGuideDBDataCorruption(String message) {
        super("DB data corruption", message);
    }
}
