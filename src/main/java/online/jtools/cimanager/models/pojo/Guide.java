package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;

public class Guide {

    private final Identifier id;
    private String title;
    private String content;

    public Guide(Identifier id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Identifier getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
