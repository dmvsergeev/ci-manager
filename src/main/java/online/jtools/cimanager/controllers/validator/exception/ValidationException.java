package online.jtools.cimanager.controllers.validator.exception;

public abstract class ValidationException extends RuntimeException {
    private final String code;

    public ValidationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
