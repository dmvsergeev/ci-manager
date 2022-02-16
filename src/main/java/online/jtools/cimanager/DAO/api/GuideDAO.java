package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.Guide;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface GuideDAO {

    @NotNull List<Guide> guides();

    @NotNull Guide get(@NotNull Identifier id);

    @NotNull Guide save(@NotNull Guide guide);

    @NotNull List<Guide> getAll();

}
