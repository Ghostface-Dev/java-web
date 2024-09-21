package ghostface.dev.connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

final class MyServerThread extends Thread {

    private final @NotNull MyServer server;
    private final @NotNull ServerSocket socket;
    private final @NotNull Selector selector;

    public MyServerThread(@NotNull MyServer server) {
        super("Server Thread");
        setDaemon(false);

        @Nullable ServerSocket socket = server.getSocket();
        @Nullable Selector selector = server.getSelector();

        if (socket == null || !socket.isBound() || selector == null) {
            throw new IllegalArgumentException("Server is not active");
        }

        this.server = server;
        this.socket = socket;
        this.selector = selector;
    }

    @Override
    public void run() {
        System.out.println("Server is running on " + server.getAddress().getPort() + " port");

        while (socket.isBound() && selector.isOpen()) {
            @NotNull Iterator<@NotNull SelectionKey> keyIterator;

            try {
                int request = selector.select();
                if (request == 0) continue;
                keyIterator = selector.selectedKeys().iterator();
            } catch (ClosedSelectorException e) {
                break;
            } catch (IOException e) {
                continue;
            }

            while (keyIterator.hasNext()) {
                @NotNull SelectionKey key = keyIterator.next();
                keyIterator.remove();

                try {
                    if (key.isAcceptable()) {
                        try {
                            @NotNull SocketChannel channel = socket.accept().getChannel();
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                            @NotNull Client client = new Client(channel, server);
                            server.getClients().put(client.getChannel(), client);
                        } catch (@NotNull Throwable throwable) {
                            throw new SocketException("An error ocurred while waiting for connection");
                        }
                    }

                    if (key.isReadable()) {
                        @NotNull SocketChannel channel = (SocketChannel) key.channel();
                        @NotNull Client client = server.getClients().get(channel);

                        try {
                            @Nullable String message = client.read();
                            if (message != null) {
                                System.out.println("Client: '" + client.getChannel().getLocalAddress() + "' write a message: '" + message + "'");

                                client.write("HTTP/1.1 200 OK\r\n" +
                                        "Content-Type: text/html; charset=UTF-8\r\n" +
                                        "Content-Length: " + 137 +
                                        "Connection: close" + "\r\n\r\n" +
                                        "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Teste com Favicon</title><link rel=\"icon\" type=\"image/png\" href=\"favicon.png\"></head><body><h1>Test favicon</h1></body></html>"
                                );
                            }
                        } catch (IOException e) {
                            throw new SocketException();
                        }

                    }

                } catch (SocketException e) {
                    try {
                        System.err.println(e.getMessage());
                        @NotNull SocketChannel channel = (SocketChannel) key.channel();
                        server.getClients().get(channel).close();
                    } catch (IOException ignore) {}
                }
            }
        }
    }
}
