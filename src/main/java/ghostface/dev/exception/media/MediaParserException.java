package ghostface.dev.exception.media;

public class MediaParserException extends Exception {
    public MediaParserException(String message) {
        super(message);
    }

    public MediaParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
