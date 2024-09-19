package ghostface.dev.main;

import ghostface.dev.connection.MyServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;

public final class Main {
    public static void main(String[] args) {
        @NotNull MyServer server = new MyServer(new InetSocketAddress(80));

        try {
            if (!server.start()) {
                System.err.println("Cannot start server");
            } else {
                System.out.println("Connecting...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
