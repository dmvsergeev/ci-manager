package online.jtools.cimanager.controllers.validator.exception;

public abstract class CimanagerException extends RuntimeException {
    private final String code;

    public CimanagerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
