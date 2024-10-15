package ghostface.dev.http.exception.header;

import ghostface.dev.http.exception.HttpException;

public class HeaderException extends HttpException {
    public HeaderException(String message) {
        super(message);
    }

    public HeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
