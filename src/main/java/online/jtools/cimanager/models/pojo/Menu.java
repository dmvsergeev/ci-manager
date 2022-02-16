package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.security.Permission;
import online.jtools.cimanager.security.Role;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    @NotNull
    private final List<Role> roles;
    @NotNull
    private final List<MenuLink> links = new ArrayList<>();

    public Menu(@NotNull List<Role> roles) {
        this.roles = roles;
    }

    @NotNull
    public Menu addLink(Permission permission, String name, String path) {
        if (isAccessible(permission)) links.add(new MenuLink(name, path));
        return this;
    }

    private boolean isAccessible(Permission permission) {
        for (Role role : roles) {
            if (role.isAccessible(permission)) return true;
        }
        return false;
    }

    @NotNull
    public List<MenuLink> links() {
        return links;
    }
}
