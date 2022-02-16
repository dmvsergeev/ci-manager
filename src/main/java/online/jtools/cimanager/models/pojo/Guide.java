package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;
import org.jetbrains.annotations.NotNull;

public class Guide {

    @NotNull
    private final Identifier id;
    @NotNull
    private final String title;
    @NotNull
    private final String content;

    public Guide(@NotNull Identifier id, @NotNull String title, @NotNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @NotNull
    public Identifier getId() {
        return id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getContent() {
        return content;
    }
}
