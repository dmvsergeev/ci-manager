package online.jtools.cimanager.models.api;

import java.util.UUID;

public class DefaultIdentifier implements Identifier {
    private final String id;

    public DefaultIdentifier(String id) {
        this.id = id;
    }

    public static Identifier generateId() {
        final String id = UUID.randomUUID().toString();
        return new DefaultIdentifier(id);
    }

    public String getId() {
        return id;
    }
}
