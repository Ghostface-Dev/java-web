package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HttpBody extends Closeable {

    static @NotNull HttpBody create(@NotNull InputStream stream) throws IOException {
        if (stream.available() >= 8192) {
            return new HttpCacheBody(stream);
        } else {
            return new HttpBufferedBody(stream);
        }
    }

    static @NotNull HttpBody create(byte[] bytes) throws IOException {
        if (bytes.length >= 8192) {
            return new HttpCacheBody(bytes);
        } else {
            return new HttpBufferedBody(bytes);
        }
    }

    @NotNull InputStream getInputStream() throws IOException;

    @NotNull OutputStream getOutputStream() throws IOException;

    int getSize();

}
