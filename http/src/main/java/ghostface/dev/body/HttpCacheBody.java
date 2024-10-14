package ghostface.dev.body;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

public final class HttpCacheBody implements HttpBody {

    private final @NotNull File file;
    private final int lengh;
    private volatile boolean close = false;

    public HttpCacheBody(@NotNull InputStream inputStream) throws IOException {
        this.file = File.createTempFile("ghostface", "-cache_body");
        this.file.deleteOnExit();
        this.lengh = update(inputStream);
    }

    public HttpCacheBody(byte @NotNull [] bytes) throws IOException {
        this.file = File.createTempFile("ghostface", "-cache_body");
        this.file.deleteOnExit();
        this.lengh = bytes.length;
        update(new ByteArrayInputStream(bytes));
    }

    private int update(@NotNull InputStream inputStream) throws IOException {
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
        return Files.newInputStream(file.toPath());
    }

    @Override
    public void write(@NotNull OutputStream outputStream) throws IOException {
        @NotNull InputStream inputStream = getInputStream();
        byte[] bytes = new byte[4096]; // 4kb

        int read;
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
            outputStream.flush();
        }
    }

    @Override
    public boolean isClose() {
        return false;
    }

    @Override
    public int length() {
        return lengh;
    }

    @Override
    public void close() throws IOException {
        if (close) {
            throw new IOException("This body already is close");
        } else try {
            Files.delete(file.toPath());
        } finally {
            close = true;
        }
    }
}
