package ghostface.dev.connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public final class MyServer implements Closeable {

    private final @NotNull Map<@NotNull SocketChannel, @NotNull Client> clients = new HashMap<>();
    private final @NotNull InetSocketAddress address;
    private @Nullable MyServerThread thread;
    private @Nullable ServerSocket socket;
    private @Nullable Selector selector;

    public MyServer(@NotNull InetSocketAddress address) {
        this.address = address;
    }

    public synchronized boolean start() throws IOException {
        if ((getSocket() != null && getSocket().isBound()) || selector != null) {
            return false;
        }

        this.selector = Selector.open();

        @NotNull ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_ACCEPT);
        channel.bind(this.getAddress());

        this.socket = channel.socket();

        this.thread = new MyServerThread(this);
        this.thread.start();

        return true;
    }

    @Override
    public synchronized void close() throws IOException {

        if (getSocket() == null || !getSocket().isBound() || getSelector() == null || thread == null) {
            return;
        }

        for (@NotNull Client client : clients.values()) {
            client.close();
        }

        this.clients.clear();

        getSocket().close();
        getSelector().close();

        this.thread.interrupt();
        this.thread = null;
        this.socket = null;
        this.selector = null;
    }

    public @NotNull InetSocketAddress getAddress() {
        return address;
    }

    @Nullable ServerSocket getSocket() {
        return socket;
    }

    @Nullable Selector getSelector() {
        return selector;
    }

    @NotNull Map<SocketChannel, Client> getClients() {
        return clients;
    }
}
