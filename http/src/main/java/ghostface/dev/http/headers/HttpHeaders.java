package ghostface.dev.http.headers;

import com.google.gson.JsonElement;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class HttpHeaders implements Iterable<HttpHeader<?>> {

    private final @NotNull Set<@NotNull HttpHeader<?>> headers;
    private final @NotNull Target target;

    public HttpHeaders(@NotNull Target target, @NotNull HttpHeader<?> @NotNull ... headers) {
        this.target = target;
        this.headers = Arrays.stream(headers).collect(Collectors.toSet());

        for (@NotNull HttpHeader<?> header : headers) {
            if (header.getTarget() != target) {
                throw new IllegalArgumentException("Header targets do not match");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> @NotNull Optional<HttpHeader<T>> getHeader(@NotNull HttpHeaderName<T> name) {
        return stream().filter(h -> h.getKey().equals(name)).map(h-> (HttpHeader<T>) h).findFirst();
    }

    public @NotNull Optional<MediaType<?>> getMediaType() {
        @Nullable HttpHeader<?> header = headers.stream().filter(httpHeader -> httpHeader.getValue() instanceof MediaType<?>).findFirst().orElse(null);

        return Optional.ofNullable((MediaType<?>) header);
    }

    @SuppressWarnings("unchecked")
    public @NotNull Optional<MediaType<JsonElement>> getJsonMediaType() {
        @Nullable MediaType<?> mediaType = getMediaType().orElse(null);

        return mediaType != null && mediaType.getData() instanceof JsonElement ? Optional.of((MediaType<JsonElement>) mediaType) : Optional.empty();
    }

    public @NotNull Target getTarget() {
        return target;
    }

    public int size() {
        return headers.size();
    }

    public boolean isEmpty() {
        return headers.isEmpty();
    }

    public boolean contains(@NotNull HttpHeader<?> header) {
        return headers.contains(header);
    }

    @Override
    public @NotNull Iterator<@NotNull HttpHeader<?>> iterator() {
        return headers.iterator();
    }

    public boolean put(@NotNull HttpHeader<?> header) {
        remove(header);
        return add(header);
    }

    public boolean add(@NotNull HttpHeader<?> header) {
        if (header.getTarget() != Target.BOTH && header.getTarget() != getTarget()) {
            return false;
        } else {
            return headers.add(header);
        }
    }

    public boolean remove(@NotNull HttpHeader<?> header) {
        return headers.remove(header);
    }

    public boolean addAll(@NotNull Collection<? extends @NotNull HttpHeader<?>> c) {
        return headers.addAll(c);
    }

    public boolean removeIf(Predicate<? super @NotNull HttpHeader<?>> filter) {
        return headers.removeIf(filter);
    }

    public void clear() {
        headers.clear();
    }

    public Stream<@NotNull HttpHeader<?>> stream() {
        return headers.stream();
    }

    @Override
    public @NotNull String toString() {
        @NotNull StringBuilder builder = new StringBuilder();

        for (@NotNull HttpHeader<?> header : headers) {
            builder.append(header.getAsString()).append("\r\n");
        }

        return builder.toString();
    }
}
