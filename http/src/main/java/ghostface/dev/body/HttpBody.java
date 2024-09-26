package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HttpBody extends Closeable {

    static @NotNull HttpBody create(@NotNull InputStream inputStream) throws IOException {
        if (inputStream.available() <= 8192) {
            return new HttpBufferedBody(inputStream);
        } else {
            return new HttpCacheBody(inputStream);
        }
    }

    static @NotNull HttpBody create(byte @NotNull[] bytes) throws IOException {
        if (bytes.length <= 8192) {
            return new HttpBufferedBody(bytes);
        } else {
            return new HttpCacheBody(bytes);
        }
    }

    @NotNull InputStream getInputStream() throws IOException;

    void write(@NotNull OutputStream stream) throws IOException;

    int size();
}
