package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

final class HttpBufferedBody implements HttpBody {

    private byte @NotNull [] bytes;
    private final int size;

    private volatile boolean closed = false;

    public HttpBufferedBody(byte[] bytes) {
        this.bytes = bytes;
        this.size = bytes.length;
    }

    public HttpBufferedBody(@NotNull InputStream stream) throws IOException {
        try (@NotNull ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[8192];

            int read;
            while ((read = stream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
            }

            this.bytes = output.toByteArray();
            this.size = bytes.length;
        }
    }

    @Override
    public @NotNull InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("This HTTP body already is closed");
        } else try {
            bytes = new byte[0];
        } finally {
            closed = true;
        }
    }
}
