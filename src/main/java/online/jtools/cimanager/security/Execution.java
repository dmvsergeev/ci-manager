package online.jtools.cimanager.security;

import online.jtools.cimanager.controllers.validator.exception.NoPermissionException;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Execution {
    private User user;

    public Execution(User user) {
        this.user = user;
    }

    public <T> T exec(@NotNull final Permission permission, @NotNull final Supplier<T> execution) {
        for (Role role : user.getRoles()) {
            if (role.isAccessible(permission)) return execution.get();
        }
        throw new NoPermissionException(permission.name());
    }
}
