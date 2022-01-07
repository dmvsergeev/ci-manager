package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.api.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.Set;

public class User extends Model {

    private final Identifier id;
    private boolean valid_id;
    private final String name;
    private boolean valid_name;
    private final String email;
    private final String username;
    private final String password;
    private final boolean active;
    private final Set<Role> roles;

    public User(Identifier id, String name, String email, String username, String password, boolean active, Set<Role> roles) {
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

    public User(Identifier id, String name, String email, String username) {
        this(id, name, email, username, "", false, Collections.emptySet());
    }

    public User(String name, String email, String username) {
        this(DefaultIdentifier.generateId(), name, email, username, "", false, Collections.emptySet());
    }

    public User(Identifier id) {
        this(id, null, null, null, "", false, Collections.emptySet());
        valid_id = true;
        valid_name = false;
    }

    public Identifier getId() {
        return id;
    }

    public String getName() {
        if (!valid_name) throw new RuntimeException();
        assertField("User.name", name, null);
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        assertField("User.username", username, null);
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
