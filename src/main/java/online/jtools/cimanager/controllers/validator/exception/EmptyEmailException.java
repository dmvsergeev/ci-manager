package online.jtools.cimanager.controllers.validator.exception;

public class EmptyEmailException extends CimanagerException {
    public EmptyEmailException(String message) {
        super("Empty email", message);
    }
}
