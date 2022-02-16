package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.GuideDAO;
import online.jtools.cimanager.DAO.database.exception.DBDataCorruption;
import online.jtools.cimanager.DAO.database.exception.NotFoundException;
import online.jtools.cimanager.DAO.database.mapper.GuideDatabaseMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.Guide;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuideDatabase implements GuideDAO {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuideDatabase(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    @Override
    public List<Guide> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.id as id_guide, " +
                "a.title as guide_title, " +
                "a.content as guide_content " +
                "FROM public.\"Guides\" as a ", new GuideDatabaseMapper());
    }

    @NotNull
    @Override
    public List<Guide> guides() {
        return null;
    }

    @NotNull
    @Override
    public Guide get(@NotNull Identifier id) {
        final List<Guide> guides = jdbcTemplate.query("SELECT " +
                "a.title, a.content, a.id as id_guide " +
                "FROM public.\"Guides\" as a " +
                "WHERE a.id = ?", new GuideDatabaseMapper(), id.toString());

        if (guides.isEmpty()) {
            throw new NotFoundException("Guide " + id + " was not found");
        } else if (guides.size() > 1) {
            throw new DBDataCorruption("DB guides ID " + id + " is duplicated or corrupted");
        } else {
            return guides.get(0);
        }
    }

    @NotNull
    @Override
    public Guide save(@NotNull Guide guide) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"Guides\" (\"id\", \"title\", \"content\") " +
                "VALUES(?,?,?)", guide.getId().toString(), guide.getTitle(), guide.getContent());
        if (result != 0) {
            return new Guide(new DefaultIdentifier(guide.getId().toString()), guide.getTitle(), guide.getContent());
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}