package ghostface.dev.http.exception.header;

import ghostface.dev.http.exception.HttpException;

public class HttpHeaderException extends HttpException {
    public HttpHeaderException(String message) {
        super(message);
    }
}
