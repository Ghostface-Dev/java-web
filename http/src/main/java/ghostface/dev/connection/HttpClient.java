package ghostface.dev.connection;

import ghostface.dev.request.HttpRequest;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.Duration;

public interface HttpClient extends Closeable {

    @NotNull Socket getSocket();

    @NotNull SocketAddress getAdress();

    @NotNull Duration getDuration();

    @NotNull HttpRequest read();

    boolean isAuthenticated();

    @Override
    void close();
}
