package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.GuideDAO;
import online.jtools.cimanager.DAO.database.exception.CimanagerGuideDBDataCorruption;
import online.jtools.cimanager.DAO.database.exception.CimanagerGuideNotFoundException;
import online.jtools.cimanager.DAO.database.mapper.GuideMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.pojo.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuideDatabase implements GuideDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuideDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private List<Guide> guides;

    public List<Guide> index() { return jdbcTemplate.query("SELECT * FROM public.\"Guides\"", new GuideMapper()); }

    public List<Guide> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.title, a.content, a.id as id_guide " +
                "FROM public.\"Guides\" as a ", new GuideMapper());
    }

    @Override
    public List<Guide> guides() {
        return null;
    }

    @Override
    public Guide get(String id) {

        final List<Guide> guides = jdbcTemplate.query("SELECT " +
                "a.title, a.content, a.id as id_guide " +
                "FROM public.\"Guides\" as a " +
                "WHERE a.id = ?", new GuideMapper(), id);

        if (guides.isEmpty()) {
            throw new CimanagerGuideNotFoundException("Guide " + id + " was not found");
        } else if (guides.size() > 1) {
            throw new CimanagerGuideDBDataCorruption("DB ID " + id + " is duplicated or corrupted");
        } else {
            return guides.get(0);
        }

    }

    @Override
    public String save(Guide guide) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"Guides\" (\"title\",\"content\") " +
                "VALUES(?,?)", guide.getTitle(), guide.getContent());
        if (result != 0) {
            return "";
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}