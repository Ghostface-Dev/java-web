package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.*;

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
    public @NotNull OutputStream getOutputStream() throws IOException {
        @NotNull ByteArrayOutputStream output = new ByteArrayOutputStream(bytes.length);

        byte[] bytes1 = new byte[8192];
        int r;
        while ((r = getInputStream().read(bytes1)) != -1) {
            output.write(bytes1, 0, r);
        }
        return output;
    }

    @Override
    public @NotNull InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public int getSize() {
        return size;
    }

    public boolean isClose() {
        return closed;
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
