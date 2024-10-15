package ghostface.dev.http.connection;

import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.HttpException;
import ghostface.dev.http.exception.body.BodyException;
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

    @NotNull HttpRequest read() throws HttpException, IOException;

    @NotNull HttpBody readBody() throws BodyException, IOException;

    void write(@NotNull HttpResponse response) throws IOException;

    default void write(@NotNull HttpBody body) throws IOException {
        body.write(getSocket().getOutputStream());
    }

    boolean isAuthenticated();

    @Override void close();

}
