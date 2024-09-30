package ghostface.dev.body;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class HttpBufferedBody implements HttpBody {

    private byte @NotNull [] bytes;
    private final int size;
    private volatile boolean closed = false;

    public HttpBufferedBody(byte[] bytes) {
        this.bytes = bytes;
        this.size = bytes.length;
    }

    @Blocking
    public HttpBufferedBody(@NotNull InputStream stream) throws IOException {
        try (@NotNull ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[4096];

            int read;
            while ((read = stream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
            }

            this.bytes = output.toByteArray();
            this.size = this.bytes.length;
        }
    }

    @Override
    public @NotNull InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void write(@NotNull OutputStream stream) throws IOException {
        stream.write(bytes);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("This HTTP body already is closed");
        } else {
            bytes = new byte[0];
            closed = true;
        }
    }
}
