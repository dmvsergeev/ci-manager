package online.jtools.cimanager.controllers.validator.exception;

public class NoPermissionException extends ValidationException {
    public NoPermissionException(String message) {
        super("permission", message);
    }
}
