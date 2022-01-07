package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.AppDAO;
import online.jtools.cimanager.DAO.database.mapper.AppMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.pojo.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppDatabase implements AppDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<App> users;

    public List<App> index() { return jdbcTemplate.query("SELECT * FROM public.\"Apps\"", new AppMapper()); }

    public List<App> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.name, a.url, a.id as id_app " +
                "FROM public.\"Apps\" as a ", new AppMapper());
    }

    @Override
    public List<App> apps() {
        return null;
    }

    @Override
    public App get(int id) {
        return null;
    }

    @Override
    public String save(App app) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"Apps\" (\"name\",\"url\") " +
                "VALUES(?,?)", app.getName(), app.getUrl());
        if (result != 0) {
            //roleDAO.save(id, user.getRoles());
            return "";
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}