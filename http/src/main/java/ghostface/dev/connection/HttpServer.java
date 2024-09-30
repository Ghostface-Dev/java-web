package ghostface.dev.connection;

import ghostface.dev.request.HttpRequest;
import ghostface.dev.response.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.util.Collection;

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

    public final boolean isClosed() {
        return socket == null || selector == null;
    }

    public final void setAdress(@NotNull SocketAddress adress) {
        if (isClosed()) throw new IllegalStateException("Cannot set adress because Server is running");
        this.adress = adress;
    }

    public abstract @NotNull Collection<@NotNull HttpClient> getClients();
    public abstract @NotNull HttpResponse process(@NotNull HttpRequest request);
    public abstract void write(@NotNull HttpResponse response, @NotNull HttpClient client) throws IOException;
    public abstract boolean start() throws IOException;
}
