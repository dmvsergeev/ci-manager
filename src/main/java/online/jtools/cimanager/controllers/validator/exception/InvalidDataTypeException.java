package online.jtools.cimanager.controllers.validator.exception;

public class InvalidDataTypeException extends ValidationException {

    public InvalidDataTypeException(String message) {
        super("Invalid data type", message);
    }
}
