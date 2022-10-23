package netgloo.exceptions;


public abstract class APIException extends Exception {
    protected APIException(String message) {
        super(message);
    }

    protected APIException(String message, Exception cause) {
        super(message, cause);
    }
}
