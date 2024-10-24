package ghostface.dev.element;

import ghostface.dev.HttpMethod;

import org.jetbrains.annotations.NotNull;

import java.net.URI;

public interface HttpRequest extends HttpElement {

    @NotNull HttpMethod getMethod();

    @NotNull URI getURI();

    // todo: add URI Authority
}
