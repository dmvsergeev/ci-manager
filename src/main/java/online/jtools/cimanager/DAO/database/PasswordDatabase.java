package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.PasswordDAO;
import online.jtools.cimanager.DAO.database.mapper.PasswordDatabaseMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.pojo.Password;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordDatabase implements PasswordDAO {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PasswordDatabase(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    @Override
    public List<Password> getForUser(@NotNull String username) {
        return jdbcTemplate.query("SELECT " +
                "u.id as id_user, " +
                "u.username, " +
                "u.email as user_email, " +
                "u.name as user_name, " +
                "a.id as id_app, " +
                "a.name as app_name, " +
                "a.url as app_url, " +
                "p.password " +
                "FROM public.\"Users\" as u " +
                "INNER JOIN public.\"Passwords\" AS p ON p.id_user = u.id " +
                "INNER JOIN public.\"Apps\" AS a ON a.id = p.id_app " +
                "WHERE u.username = ?", new PasswordDatabaseMapper(), username);
    }

    @NotNull
    public Password save(@NotNull Password password) {
        final int result;
        result = jdbcTemplate.update(
                "INSERT INTO public.\"Passwords\" (\"id_app\",\"id_user\",\"password\") " +
                        "VALUES(?,?,?) " +
                        "ON CONFLICT (\"id_app\",\"id_user\") " +
                        "DO " +
                        "UPDATE SET \"password\" = ?",
                password.getApp().getId().toString(), password.getUser().getId().toString(), password.getPassword(), password.getPassword());
        if (result != 0) {
            return new Password(password.getApp(), password.getUser(), password.getPassword());
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}