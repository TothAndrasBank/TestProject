package exceptions;


public class SQLConnectionException extends RuntimeException {

    public SQLConnectionException(final String message) {
        super(message);
    }

    public SQLConnectionException(final Throwable cause) {
        super(cause);
    }
}
