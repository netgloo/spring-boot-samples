package netgloo.exceptions;

public class APIIllegalArgumentException extends APIException {
    public APIIllegalArgumentException(String message) {
        super(message);
    }

    public APIIllegalArgumentException(String message, Exception cause) {
        super(message, cause);
    }
}
