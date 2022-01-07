package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.api.Model;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class User extends Model {
    @Nullable private final Identifier id;
    private boolean valid_id;
    @NotNull private final String name;
    private boolean valid_name;
    @NotNull private final String email;
    @NotNull private final String username;
    @NotNull private final String password;
    private final boolean active;
    @NotNull private final Set<Role> roles;

    public User(@NotNull Identifier id, @NotNull String name, @NotNull String email, @NotNull String username, @NotNull String password, boolean active, @NotNull Set<Role> roles) {
        this.id = id;
        valid_id = true;
        this.name = name;
        valid_name = true;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String username, @NotNull String password, boolean active, @NotNull Set<Role> roles) {
        this.id = null;
        valid_id = true;
        this.name = name;
        valid_name = true;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(@NotNull Identifier id, @NotNull String name, @NotNull String email, @NotNull String username) {
        this(id, name, email, username, "", false, Collections.emptySet());
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String username) {
        this(name, email, username, "", false, Collections.emptySet());
    }

    @NotNull
    public Identifier getId() {
        return Objects.requireNonNull(id);
    }

    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        if (!valid_name) throw new RuntimeException();
        assertField("User.name", name, null);
        return name;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public String getUsername() {
        assertField("User.username", username, null);
        return username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public @NotNull Set<Role> getRoles() {
        return roles;
    }
}
