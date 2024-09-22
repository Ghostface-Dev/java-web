package ghostface.dev.page;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public final class HomePage extends HtmlPage {

    public HomePage() throws IllegalArgumentException {
        super("home", Paths.get(System.getProperty("user.dir"), "frontend/home/index.html"));
    }

    @Override
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
