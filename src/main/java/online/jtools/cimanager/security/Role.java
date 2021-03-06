package online.jtools.cimanager.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static online.jtools.cimanager.security.Permission.*;

/**
 * Application roles
 */
public enum Role {
    ADMIN("ADMIN", Arrays.asList(NEWS,
            PASSWORDS,
            GUIDES,
            ALL_USERS,
            CREATE_USER,
            APPS,
            CREATE_APP)),
    GUEST("GUEST", Collections.singletonList(LOGIN)),
    USER("USER", Arrays.asList(NEWS,
            PASSWORDS,
            GUIDES));

    final private String name;
    final private List<Permission> permissions;

    /**
     * @param name role name
     * @param permissions action accessions
     */
    Role(@NotNull String name, @NotNull List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    @NotNull
    public static Role of(@Nullable String name) {
        if (name == null) return GUEST;
        for (Role role : values()) {
            if (role.name.equals(name)) return role;
        }
        return GUEST;
    }

    public boolean isAccessible(@NotNull Permission permission) {
        return permissions.contains(permission);
    }

}
