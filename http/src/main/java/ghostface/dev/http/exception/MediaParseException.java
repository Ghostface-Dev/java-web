package ghostface.dev.http.exception;

public class MediaParseException extends Exception {
    public MediaParseException(String message) {
        super(message);
    }

    public MediaParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
