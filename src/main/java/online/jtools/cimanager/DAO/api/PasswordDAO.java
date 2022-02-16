package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.pojo.Password;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PasswordDAO {
    @NotNull Password save(@NotNull Password password);

    @NotNull List<Password> getForUser(@NotNull String username);
}
