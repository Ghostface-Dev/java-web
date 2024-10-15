package ghostface.dev.http.impl.headers;

import com.google.gson.JsonElement;
import ghostface.dev.http.headers.HttpHeader;
import ghostface.dev.http.headers.HttpHeaderKey;
import ghostface.dev.http.headers.Target;
import ghostface.dev.http.media.MediaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import org.jsoup.nodes.Element;

import java.util.Objects;

public abstract class AbstractHttpHeader<T> implements HttpHeader<T> {

    public static @NotNull HttpHeader<@NotNull MediaType<@NotNull JsonElement>> CONTENT_JSON(@NotNull MediaType<@NotNull JsonElement> media) {
        return new Provider.ContentJson(media);
    }

    public static @NotNull HttpHeader<@NotNull MediaType<@NotNull Element>> CONTENT_HTML(@NotNull MediaType<@NotNull Element> media) {
        return new Provider.ContentHtml(media);
    }

    // Objects

    private final @NotNull HttpHeaderKey key;
    private final @UnknownNullability T value;
    private final @NotNull Target target;

    protected AbstractHttpHeader(@NotNull HttpHeaderKey key, @UnknownNullability T value, @NotNull Target target) {
        this.key = key;
        this.value = value;
        this.target = target;
    }

    @Override
    public @NotNull HttpHeaderKey getKey() {
        return key;
    }

    @Override
    public @UnknownNullability T getValue() {
        return value;
    }

    @Override
    public @NotNull Target getTarget() {
        return target;
    }

    @Override
    public abstract @NotNull String toString();

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull AbstractHttpHeader<?> that = (AbstractHttpHeader<?>) object;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

    // For organization

    private static final class Provider {

        private Provider() {
            throw new UnsupportedOperationException();
        }

        // Classes

        private static final class ContentJson extends AbstractHttpHeader<@NotNull MediaType<@NotNull JsonElement>> {
            public ContentJson(@NotNull MediaType<@NotNull JsonElement> media) {
                super(HttpHeaderKey.CONTENT_TYPE, media, Target.BOTH);
            }

            @Override
            public @NotNull String toString() {
                return getKey() + ": " + getValue();
            }
        }

        private static final class ContentHtml extends AbstractHttpHeader<@NotNull MediaType<@NotNull Element>> {
            public ContentHtml(@NotNull MediaType<@NotNull Element> media) {
                super(HttpHeaderKey.CONTENT_TYPE, media, Target.BOTH);
            }

            @Override
            public @NotNull String toString() {
                return getKey() + ": " + getValue();
            }
        }
    }

}
