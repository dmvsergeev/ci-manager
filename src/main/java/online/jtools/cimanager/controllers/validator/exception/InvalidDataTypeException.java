package online.jtools.cimanager.controllers.validator.exception;

public class InvalidDataTypeException extends CimanagerException {
    public InvalidDataTypeException(String message) {
        super("Data type", message);
    }
}
