package ghostface.dev.http.exception.version;

import ghostface.dev.http.exception.HttpException;

public class HttpVersionException extends HttpException {
    public HttpVersionException(String message) {
        super(message);
    }

    public HttpVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
