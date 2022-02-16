package online.jtools.cimanager.models.pojo;

import org.jetbrains.annotations.NotNull;

public class MenuLink {
    @NotNull
    private final String name;
    @NotNull
    private final String link;

    public MenuLink(@NotNull String name, @NotNull String link) {
        this.name = name;
        this.link = link;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getLink() {
        return link;
    }
}
