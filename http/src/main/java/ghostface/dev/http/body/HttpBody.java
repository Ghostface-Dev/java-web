package ghostface.dev.http.body;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HttpBody extends Closeable {

    static @NotNull HttpBody empty() {
        return new HttpBufferedBody(new byte[0]);
    }

    static @NotNull HttpBody create(@NotNull InputStream inputStream) throws IOException {
        if (inputStream.available() > 8192) {
            return new HttpCacheBody(inputStream);
        } else {
            return new HttpBufferedBody(inputStream);
        }
    }

    static @NotNull HttpBody create(byte @NotNull [] bytes) throws IOException {
        if (bytes.length > 8192) {
            return new HttpCacheBody(bytes);
        } else {
            return new HttpBufferedBody(bytes);
        }
    }

    @NotNull InputStream getInputStream() throws IOException;

    void write(@NotNull OutputStream outputStream) throws IOException;

    boolean isClose();

    int length();
}
