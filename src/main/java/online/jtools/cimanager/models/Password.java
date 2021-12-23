package online.jtools.cimanager.models;

public class Password {
    private int id;
    private int id_app;
    private int id_user;
    private String password;

    public Password() {

    }

    public Password(int id, int id_app, int id_user, String password) {
        this.id = id;
        this.id_app = id_app;
        this.id_user = id_user;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_app() {
        return id_app;
    }

    public void setId_app(int id_app) {
        this.id_app = id_app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
