package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;
import org.jetbrains.annotations.NotNull;

public class App {

    @NotNull
    private final Identifier id;
    @NotNull
    private final String name;
    @NotNull
    private final String url;

    public App(@NotNull Identifier id, @NotNull String name, @NotNull String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @NotNull
    public Identifier getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public static App id(@NotNull Identifier id) {
        return new App(id, "", "") {
            @NotNull
            @Override
            public String getName() {
                throw new UnsupportedOperationException();
            }

            @NotNull
            @Override
            public String getUrl() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
