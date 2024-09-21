package ghostface.dev.status;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class HttpStatus {

    public static @NotNull HttpStatus OK = new HttpStatus(200, "OK");
    public static @NotNull HttpStatus NOT_FOUND = new HttpStatus(404, "Not Found");

    // Objects

    private final int code;
    private final @NotNull String message;

    public HttpStatus(int code, @NotNull String message) {
        this.code = code;
        this.message = message;

        if (message.length() > 1024) {
            throw new IllegalArgumentException("This HTTP status message is to long");
        }
    }

    public int getCode() {
        return code;
    }

    public @NotNull String getMessage() {
        return message;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull HttpStatus that = (HttpStatus) object;
        return code == that.code && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
