package online.jtools.cimanager.models.pojo;

import online.jtools.cimanager.security.Permission;
import online.jtools.cimanager.security.Role;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<Role> roles;
    private final List<MenuLink> links = new ArrayList<>();

    public Menu(List<Role> roles) {
        this.roles = roles;
    }

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

    public List<MenuLink> links() {
        return links;
    }
}
