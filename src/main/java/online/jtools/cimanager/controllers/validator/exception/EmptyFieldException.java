package online.jtools.cimanager.controllers.validator.exception;

public class EmptyFieldException extends CimanagerException {
    public EmptyFieldException(String message) {
        super("Empty field", message);
    }
}
