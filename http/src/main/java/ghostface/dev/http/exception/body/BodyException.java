package ghostface.dev.http.exception.body;

import ghostface.dev.http.exception.HttpException;

public class BodyException extends HttpException {

    public BodyException(String message) {
        super(message);
    }

    public BodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
