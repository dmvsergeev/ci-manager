package online.jtools.cimanager.DAO.database.exception;

import online.jtools.cimanager.controllers.validator.exception.CimanagerException;

public class DBDataCorruption extends CimanagerException {
    public DBDataCorruption(String message) {
        super("DB data corruption", message);
    }
}
