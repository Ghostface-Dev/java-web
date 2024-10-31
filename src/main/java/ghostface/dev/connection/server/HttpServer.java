package ghostface.dev.connection.server;

import ghostface.dev.connection.client.HttpClient;
import ghostface.dev.element.HttpRequest;
import ghostface.dev.element.HttpResponse;
import ghostface.dev.exception.HttpException;
import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.util.Collection;
import java.util.Optional;

public interface HttpServer {

    @NotNull Collection<HttpClient> clients();

    default @NotNull Optional<HttpClient> getClient(@NotNull Socket socket) {
        return clients().stream().filter(client -> client.getSocket().equals(socket)).findFirst();
    }

    @NotNull HttpResponse compute(@NotNull HttpRequest request) throws HttpException;
}
