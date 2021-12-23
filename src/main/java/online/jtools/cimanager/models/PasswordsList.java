package online.jtools.cimanager.models;

public class PasswordsList {
    private int id;
    private String app;
    private String password;

    public PasswordsList() {

    }

    public PasswordsList(int id, String app, String password) {
        this.id = id;
        this.app = app;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
