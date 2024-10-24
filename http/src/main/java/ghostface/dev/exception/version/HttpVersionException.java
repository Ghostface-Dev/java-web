package ghostface.dev.exception.version;

public class HttpVersionException extends Exception {
    public HttpVersionException(String message) {
        super(message);
    }

    public HttpVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
