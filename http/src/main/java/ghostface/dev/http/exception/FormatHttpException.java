package ghostface.dev.http.exception;

public class FormatHttpException extends HttpException {
    public FormatHttpException(String message) {
        super(message);
    }

    public FormatHttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
