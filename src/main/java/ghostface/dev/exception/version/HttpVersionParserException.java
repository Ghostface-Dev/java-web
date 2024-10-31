package ghostface.dev.exception.version;

public class HttpVersionParserException extends HttpVersionException{
    public HttpVersionParserException(String message) {
        super(message);
    }

    public HttpVersionParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
