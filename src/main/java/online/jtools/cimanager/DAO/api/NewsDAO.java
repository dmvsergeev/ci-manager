package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.News;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface NewsDAO {

    @NotNull List<News> news();

    @NotNull News get(@NotNull Identifier id);

    @NotNull News save(@NotNull News guide);

    @NotNull List<News> getAll();

}
