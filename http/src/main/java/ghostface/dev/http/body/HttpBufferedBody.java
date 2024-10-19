package ghostface.dev.http.body;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public final class HttpBufferedBody implements HttpBody {

    private byte @NotNull [] bytes;
    private volatile boolean close = false;

    public HttpBufferedBody(@NotNull InputStream inputStream) throws IOException {
        try (@NotNull ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte @NotNull [] bytes = new byte[4096]; // 4 kb

            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
            }

            this.bytes = output.toByteArray();
        }
    }

    public HttpBufferedBody(byte @NotNull [] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void write(@NotNull OutputStream outputStream) throws IOException {
        if (close) {
            throw new IOException("Body is closed");
        } else try (@NotNull InputStream inputStream = getInputStream()) {
            byte[] bytes = new byte[4096]; // 4kb

            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
                outputStream.flush();
            }
        }
    }

    @Override
    public @NotNull InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void close() throws IOException {
        if (close) {
            throw new IOException("This body already is close");
        } else {
            this.bytes = new byte[0];
            close = true;
        }
    }
}
