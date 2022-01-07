package online.jtools.cimanager.models.pojo;

public class App {

    private int id;
    private String name;
    private String url;

    public App() {

    }

    public App(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public App(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
