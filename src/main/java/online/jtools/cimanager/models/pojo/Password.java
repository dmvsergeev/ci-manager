package online.jtools.cimanager.models.pojo;

import org.jetbrains.annotations.NotNull;

public class Password {
    @NotNull
    private final App app;
    @NotNull
    private final User user;
    @NotNull
    private final String password;

    public Password(@NotNull App app, @NotNull User user, @NotNull String password) {
        this.app = app;
        this.user = user;
        this.password = password;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public User getUser() {
        return user;
    }

    @NotNull
    public App getApp() {
        return app;
    }
}
