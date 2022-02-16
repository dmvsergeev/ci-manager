package online.jtools.cimanager.controllers.validator.exception;

public class DbSaveException extends CimanagerException {
    public DbSaveException(String message) {
        super("Database save", message);
    }
}