package ghostface.dev.http.exception.body;

import ghostface.dev.http.exception.HttpException;

public class HttpBodyException extends HttpException {

    public HttpBodyException(String message) {
        super(message);
    }

    public HttpBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
