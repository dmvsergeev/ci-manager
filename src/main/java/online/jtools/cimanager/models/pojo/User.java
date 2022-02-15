package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.api.Model;
import online.jtools.cimanager.security.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class User extends Model {
    @Nullable
    private final Identifier id;
    final boolean valid_id;
    @NotNull
    private final String name;
    final boolean valid_name;
    @NotNull
    private final String email;
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    private final boolean active;
    @NotNull
    private final AtomicReference<Set<Role>> roles = new AtomicReference<>();

    public User(@Nullable Identifier id, @NotNull String name, @NotNull String email, @NotNull String username, @NotNull String password, boolean active) {
        this.id = id;
        valid_id = true;
        this.name = name;
        valid_name = true;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String username, @NotNull String password, boolean active) {
        this(null, name, email, username, password, active);
    }

    public User(@NotNull Identifier id, @NotNull String name, @NotNull String email, @NotNull String username) {
        this(id, name, email, username, "", false);
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String username) {
        this(name, email, username, "", false);
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
        Set<Role> roles = this.roles.get();
        if (roles == null) {
            roles = EnumSet.noneOf(Role.class);
            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                roles.add(Role.of(authority.getAuthority()));
            }
            this.roles.compareAndSet(null, roles);
        }
        return roles;
    }
}
