package ghostface.dev;


import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public class HttpBody {

    private final @NotNull InputStream inputStream;

    public HttpBody(@NotNull InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public @NotNull InputStream getInputStream() {
        return inputStream;
    }
}
