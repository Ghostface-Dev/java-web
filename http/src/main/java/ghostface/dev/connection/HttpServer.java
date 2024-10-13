package ghostface.dev.connection;

import ghostface.dev.request.HttpRequest;
import ghostface.dev.response.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.util.Collection;
import java.util.Optional;

public abstract class HttpServer implements Closeable {

    protected @Nullable ServerSocket socket;
    protected @Nullable Selector selector;
    private @NotNull SocketAddress adress;

    protected HttpServer(@NotNull SocketAddress adress) {
        this.adress = adress;
    }

    // Getters

    public @NotNull SocketAddress getAdress() {
        return adress;
    }

    public @NotNull Optional<@NotNull HttpClient> getClient(@NotNull Socket socket) {
        return getClients().stream().filter(client -> client.getSocket().equals(socket)).findFirst();
    }

    public final boolean isClosed() {
        return socket == null || selector == null;
    }

    public final void setAdress(@NotNull SocketAddress adress) {
        if (isClosed()) throw new IllegalStateException("Cannot set adress because Server is running");
        this.adress = adress;
    }

    public abstract @NotNull Collection<@NotNull HttpClient> getClients();

    public abstract boolean start() throws IOException;

    public abstract @NotNull HttpResponse compute(@NotNull HttpRequest request);
}
