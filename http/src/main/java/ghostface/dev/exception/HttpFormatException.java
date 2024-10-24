package ghostface.dev.exception;

public class HttpFormatException extends HttpException {
    public HttpFormatException(String message) {
        super(message);
    }

    public HttpFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
