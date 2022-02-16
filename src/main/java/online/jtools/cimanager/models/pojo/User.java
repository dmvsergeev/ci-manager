package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.security.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class User {
    @NotNull
    private final Identifier id;
    @NotNull
    private final String name;
    @NotNull
    private final String email;
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    private final boolean active;
    @NotNull
    private final AtomicReference<Set<Role>> roles = new AtomicReference<>();

    public User(@NotNull Identifier id, @NotNull String name, @NotNull String email, @NotNull String username, @NotNull String password, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public User(@NotNull Identifier id, @NotNull String name, @NotNull String email, @NotNull String username) {
        this(id, name, email, username, "", false);
    }

    @NotNull
    public Identifier getId() {
        return Objects.requireNonNull(id);
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    @NotNull
    public Set<Role> getRoles() {
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

    public static User id(@NotNull Identifier id) {
        return new User(id, "", "", "") {
            @Override
            public @NotNull String getName() {
                throw new UnsupportedOperationException();
            }

            @Override
            public @NotNull String getEmail() {
                throw new UnsupportedOperationException();
            }

            @Override
            public @NotNull String getUsername() {
                throw new UnsupportedOperationException();
            }

            @Override
            public @NotNull String getPassword() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isActive() {
                throw new UnsupportedOperationException();
            }

            @Override
            public @NotNull Set<Role> getRoles() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
