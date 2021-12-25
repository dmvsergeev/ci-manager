package online.jtools.cimanager.models.api;

public class DefaultIdentifier implements Identifier {
    private final int id;

    public DefaultIdentifier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
