package ghostface.dev.body;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

public class HttpCacheBody implements HttpBody {

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

    @Blocking
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
    public void write(@NotNull OutputStream stream) throws IOException {
        @NotNull InputStream input = getInputStream();
        byte[] bytes = new byte[8192]; // 8KB

        int read;
        while ((read = input.read(bytes)) != -1) {
            stream.write(bytes, 0, read);
            stream.flush();
        }
    }

    @Override
    public int size() {
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
