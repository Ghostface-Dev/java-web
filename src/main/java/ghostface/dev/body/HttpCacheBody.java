package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

final class HttpCacheBody implements HttpBody {

    private final @NotNull File file;
    private final int size;

    private volatile boolean closed = false;

    public HttpCacheBody(@NotNull InputStream stream) throws IOException {
        this.file = File.createTempFile("ghostface", "-cache_body");
        this.file.deleteOnExit();
        this.size = uptdate(stream);
    }

    public HttpCacheBody(byte[] bytes) throws IOException {
        this.file = File.createTempFile("ghostface", "-cache_body");
        this.file.deleteOnExit();
        this.size = bytes.length;
        uptdate(new ByteArrayInputStream(bytes));
    }

    private int uptdate(@NotNull InputStream inputStream) throws IOException {
        try (@NotNull FileOutputStream output = new FileOutputStream(file)) {
            byte[] bytes = new byte[8192];

            int size = 0;
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                output.write(bytes, 0, read);
                output.flush();
                size += read;
            }
            return size;
        }
    }

    @Override
    public @NotNull InputStream getInputStream() throws IOException {
        if (closed) {
            throw new IOException("This Http Body already is closed");
        } else {
            return Files.newInputStream(file.toPath());
        }
    }

    @Override
    public @NotNull OutputStream getOutputStream() throws IOException {
        if (closed) {
            throw new IOException("This Http Body already is closed");
        } else {
            return Files.newOutputStream(file.toPath());
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("Http body already closed");
        } else try {
            Files.delete(file.toPath());
        } finally {
            closed = true;
        }
    }
}
