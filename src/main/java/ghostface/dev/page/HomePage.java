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


}
