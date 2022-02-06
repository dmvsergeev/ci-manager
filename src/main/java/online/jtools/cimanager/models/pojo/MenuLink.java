package online.jtools.cimanager.models.pojo;

public class MenuLink {

    private int id;
    private String name;

    public MenuLink(String name, String link) {
        this.name = name;
        this.link = link;
    }

    private String link;


    public MenuLink(int id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
