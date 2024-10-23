package ghostface.dev.http.connection.server;

import ghostface.dev.http.connection.client.HttpClient;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.HttpException;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Optional;

public interface HttpServer {

    @NotNull Collection<HttpClient> clients();

    default @NotNull Optional<HttpClient> getClient(@NotNull Socket socket) {
        return clients().stream().filter(client -> client.getSocket().equals(socket)).findFirst();
    }

    @NotNull HttpResponse compute(@NotNull HttpRequest request) throws HttpException;
}
