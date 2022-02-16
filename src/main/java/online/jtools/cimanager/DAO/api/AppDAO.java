package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface AppDAO {
    @NotNull List<App> apps();

    @NotNull App get(@NotNull Identifier id);

    @NotNull App save(@NotNull App app);

    @NotNull List<App> getAll();
}
