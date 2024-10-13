package ghostface.dev.connection;

import ghostface.dev.request.HttpRequest;
import ghostface.dev.response.HttpResponse;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.Duration;

public interface HttpClient extends Closeable {

    @NotNull Socket getSocket();

    @NotNull SocketAddress getAdress();

    @NotNull Duration getDuration();

    @NotNull HttpRequest read();

    void write(@NotNull HttpResponse response) throws IOException;

    boolean isAuthenticated();

    @Override
    void close();
}
