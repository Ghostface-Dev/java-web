package ghostface.dev.page;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlPage {

    protected @Nullable File file;
    private final @NotNull String name;
    private final @NotNull Path path;

    protected HtmlPage(@NotNull String name, @NotNull Path path) throws IllegalArgumentException {
        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException("This path '" + path + "' is not a html file");
        }
        this.file = path.toFile();
        this.name = name;
        this.path = path;
    }

    // Getters

    public final @NotNull InputStream getInputStream() throws IOException {
        if (file == null) {
            throw new IOException("This http body page is closed");
        } else {
            return Files.newInputStream(file.toPath());
        }
    }

    public void close() throws IOException {
        if (file == null) {
            throw new IOException("This Http body page already closed");
        } else {
            this.file = null;
        }
    }

    public final @NotNull String getName() {
        return name;
    }

    public final @NotNull Path getPath() {
        return path;
    }

    public byte[] getBytes() throws IOException {
        @NotNull InputStream in = getInputStream();
        @NotNull ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];

        int r;
        while ((r = in.read(bytes)) != -1) {
            outputStream.write(bytes, 0, r);
            outputStream.flush();
        }

        return outputStream.toByteArray();
    }
}
