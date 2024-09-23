package ghostface.dev.header;


import ghostface.dev.header.content.ContentHeader;
import ghostface.dev.header.general.GeneralHeader;
import org.jetbrains.annotations.NotNull;

public abstract class HttpHeader {

    private final @NotNull GeneralHeader general;
    private final @NotNull ContentHeader content;
    private final @NotNull MediaType<?> mediaType;

    protected HttpHeader(
            @NotNull GeneralHeader general,
            @NotNull ContentHeader content,
            @NotNull MediaType<?> mediaType)
    {
        this.general = general;
        this.content = content;
        this.mediaType = mediaType;
    }

    public @NotNull GeneralHeader getGeneral() {
        return general;
    }

    public @NotNull ContentHeader getContent() {
        return content;
    }

    public @NotNull MediaType<?> getMediaType() {
        return mediaType;
    }
}
