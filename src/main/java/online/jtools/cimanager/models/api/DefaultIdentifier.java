package online.jtools.cimanager.models.api;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DefaultIdentifier implements Identifier {
    private final String id;
    private final IdentifierType type;

    public DefaultIdentifier(@NotNull String id) {
        this(id, IdentifierType.STORED);
    }

    private DefaultIdentifier(@NotNull String id, IdentifierType type) {
        this.id = id;
        this.type = type;
    }

    public static DefaultIdentifier generateId() {
        final String id = UUID.randomUUID().toString();
        return new DefaultIdentifier(id, IdentifierType.NEW);
    }

    @Override
    public String toString() {
        return id;
    }

    @NotNull
    @Override
    public IdentifierType type() {
        return type;
    }
}
