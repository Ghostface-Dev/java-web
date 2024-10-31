package ghostface.dev.connection.client;

import ghostface.dev.body.HttpBody;
import ghostface.dev.exception.HttpException;
import ghostface.dev.element.HttpRequest;
import ghostface.dev.element.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public interface HttpClient extends Closeable {

    @NotNull Socket getSocket();

    @UnknownNullability HttpRequest read() throws HttpException, IOException;

    void write(@NotNull HttpResponse response) throws IOException;

    void write(@NotNull HttpBody body) throws IOException;

    @Override
    void close() throws IOException;

}
