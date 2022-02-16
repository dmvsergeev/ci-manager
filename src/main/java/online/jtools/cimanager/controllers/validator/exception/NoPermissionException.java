package online.jtools.cimanager.controllers.validator.exception;

public class NoPermissionException extends CimanagerException {
    public NoPermissionException(String message) {
        super("Permission", message);
    }
}
