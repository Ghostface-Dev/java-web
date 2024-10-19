package ghostface.dev.http.connection.server;

import ghostface.dev.http.connection.client.HttpClient;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.HttpException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface HttpServer {

    @NotNull Collection<HttpClient> clients();

    @NotNull CompletableFuture<HttpResponse> compute(@NotNull HttpRequest request) throws HttpException;
}
