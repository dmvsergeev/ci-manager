package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;
import org.jetbrains.annotations.Nullable;

public class News {

    @Nullable
    private final Identifier id;
    private String title;
    private String content;
    private String img;

    public News(@Nullable Identifier id, String title, String content, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public News(@Nullable Identifier id, String title, String content) {
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

    public String getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
