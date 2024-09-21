package ghostface.dev.body;


import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.InputStream;

public interface HttpBody extends Closeable {

    @NotNull InputStream getInputStream();

}
