package ghostface.dev.network;

import ghostface.dev.config.Authentication;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Collection;

public abstract class SocketDatabase implements Closeable {

    protected @Nullable ServerSocket socket;
    private @NotNull InetSocketAddress address;
    private @NotNull Authentication login;

    public SocketDatabase(@NotNull InetSocketAddress address, @NotNull Authentication login) {
        this.address = address;
        this.login = login;
    }

    public abstract boolean start() throws IOException;

    @Override
    public abstract void close() throws IOException;

    public @NotNull InetSocketAddress getAddress() {
        return address;
    }

    public @NotNull Authentication getLogin() {
        return login;
    }

    public synchronized final void setAddress(@NotNull InetSocketAddress address) {
        if (socket != null) throw new IllegalStateException("Cannot set addres cause Socket is active");
        this.address = address;
    }

    public synchronized final void setLogin(@NotNull Authentication login) {
        if (socket != null) {
            throw new IllegalStateException("Cannot set login info cause Socket is active");
        } else {
            this.login = login;
        }
    }
}
