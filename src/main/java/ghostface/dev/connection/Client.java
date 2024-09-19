package ghostface.dev.connection;

import ghostface.dev.response.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

final class Client implements Closeable {

    private final @NotNull SocketChannel channel;
    private final @NotNull MyServer server;

    public Client(@NotNull SocketChannel channel, @NotNull MyServer server) {
        this.channel = channel;
        this.server = server;
    }

    @Override
    public void close() throws IOException {
        server.getClients().remove(channel, this);
        channel.close();
    }

    public @Nullable String read() throws IOException {
        @NotNull ByteBuffer buffer = ByteBuffer.allocate(4096);
        @NotNull StringBuilder builder = new StringBuilder();

        int bytes = getChannel().read(buffer);

        if (bytes == -1) {
            close();
            return null;
        } else if (bytes == 0) {
            return null;
        } else while (bytes > 0) {
            buffer.flip();
            builder.append(StandardCharsets.UTF_8.decode(buffer));
            buffer.clear();

            bytes = getChannel().read(buffer);
        }

        return builder.toString();
    }

    public @NotNull SocketChannel getChannel() {
        return channel;
    }

}
