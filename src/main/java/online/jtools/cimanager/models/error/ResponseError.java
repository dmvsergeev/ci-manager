package online.jtools.cimanager.models.error;

public class ResponseError {
    private final String code;
    private final String message;

    public ResponseError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
