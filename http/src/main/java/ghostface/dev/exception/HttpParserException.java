package ghostface.dev.exception;

public class HttpParserException extends HttpException {
    public HttpParserException(String message) {
        super(message);
    }

    public HttpParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
