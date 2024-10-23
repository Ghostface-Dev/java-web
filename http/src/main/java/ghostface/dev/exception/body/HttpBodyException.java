package ghostface.dev.exception.body;

import ghostface.dev.exception.HttpException;

public class HttpBodyException extends HttpException {

    public HttpBodyException(String message) {
        super(message);
    }

    public HttpBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
