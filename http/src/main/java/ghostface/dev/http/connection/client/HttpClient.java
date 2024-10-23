package ghostface.dev.http.connection.client;

import ghostface.dev.http.body.HttpBody;
import ghostface.dev.http.element.HttpRequest;
import ghostface.dev.http.element.HttpResponse;
import ghostface.dev.http.exception.HttpException;
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
