package ghostface.dev.sock;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public final class MyServer implements Closeable {

    private final @NotNull InetSocketAddress address;
    private @Nullable ServerSockThread thread;
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
        channel.bind(this.address);

        this.socket = channel.socket();

        this.thread = new ServerSockThread(this);
        this.thread.start();

        return true;
    }

    @Override
    public synchronized void close() throws IOException {
        // TODO close
    }

    public @NotNull InetSocketAddress getAddress() {
        return address;
    }

    public @Nullable ServerSocket getSocket() {
        return socket;
    }

    public @Nullable Selector getSelector() {
        return selector;
    }
}
