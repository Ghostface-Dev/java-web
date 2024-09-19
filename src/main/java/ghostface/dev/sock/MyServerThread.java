package ghostface.dev.sock;

import org.jetbrains.annotations.NotNull;

final class MyServerThread extends Thread {

    private final @NotNull MyServer server;

    public MyServerThread(@NotNull MyServer server) {
        super("Server_Socket_Thread");
        setDaemon(false);

        this.server = server;

    }

    @Override
    public void run() {
        super.run();
    }
}
