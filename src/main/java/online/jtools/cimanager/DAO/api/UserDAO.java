package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface UserDAO {
    @NotNull List<User> list();

    @NotNull User get(Identifier id);

    @NotNull User save(@NotNull User user);
}
