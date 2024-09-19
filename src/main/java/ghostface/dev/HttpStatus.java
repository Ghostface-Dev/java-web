package ghostface.dev;

import org.jetbrains.annotations.NotNull;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final @NotNull String message;

    HttpStatus(int code, @NotNull String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public @NotNull String getMessage() {
        return message;
    }
}
