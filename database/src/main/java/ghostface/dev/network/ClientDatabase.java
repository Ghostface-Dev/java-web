package ghostface.dev.network;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;

public interface ClientDatabase extends Closeable {

    @NotNull Socket getSocket();

    @NotNull SocketAddress getAddres();

    void write(@NotNull InputStream stream) throws IOException;

    void write(byte @NotNull [] bytes) throws IOException;

    @Override
    void close() throws IOException;
}
