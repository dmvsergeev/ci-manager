package online.jtools.cimanager.controllers.validator.exception;

public abstract class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
