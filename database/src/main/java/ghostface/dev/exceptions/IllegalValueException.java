package ghostface.dev.exceptions;

public class IllegalValueException extends RuntimeException {
    public IllegalValueException() {
    }

    public IllegalValueException(String message) {
        super(message);
    }

    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
