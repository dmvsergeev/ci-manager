package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.AppDAO;
import online.jtools.cimanager.DAO.database.mapper.AppDatabaseMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppDatabase implements AppDAO {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppDatabase(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    @Override
    public List<App> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.id as id_app, " +
                "a.name as app_name, " +
                "a.url as app_url " +
                "FROM public.\"Apps\" as a ", new AppDatabaseMapper());
    }

    @NotNull
    @Override
    public List<App> apps() {
        return null;
    }

    @NotNull
    @Override
    public App get(@NotNull Identifier id) {
        return null;
    }

    @NotNull
    @Override
    public App save(@NotNull App app) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"Apps\" (\"id\",\"name\",\"url\") " +
                "VALUES(?,?,?)", app.getId().toString(), app.getName(), app.getUrl());
        if (result != 0) {
            return new App(new DefaultIdentifier(app.getId().toString()), app.getName(), app.getUrl());
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}