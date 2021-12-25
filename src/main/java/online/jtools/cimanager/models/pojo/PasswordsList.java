package online.jtools.cimanager.models.pojo;

public class PasswordsList {
    private final int id;
    private final App app;
    private final User user;
    private final String password;

    public PasswordsList(int id, String password, App app, User user) {
        this.id = id;
        this.password = password;
        this.app = app;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public App getApp() {
        return app;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
