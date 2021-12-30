package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.database.mapper.PasswordMapper;
import online.jtools.cimanager.DAO.database.mapper.PasswordsListMapper;
import online.jtools.cimanager.DAO.api.PasswordDAO;
import online.jtools.cimanager.models.pojo.Password;
import online.jtools.cimanager.models.pojo.PasswordsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordDatabase implements PasswordDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PasswordDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int USERS_COUNT;

    private List<Password> passwords;

    public List<Password> index() {
        return jdbcTemplate.query("SELECT * FROM public.\"Passwords\"", new PasswordMapper());
    }

    public List<PasswordsList> getForUser() {
        return jdbcTemplate.query("SELECT " +
                "u.id as id_user, u.username, u.email, u.name as user_name, " +
                "p.id as id_password, p.password," +
                "a.name as app_name, a.url, a.id as id_app " +
                "FROM public.\"Users\" as u " +
                "INNER JOIN public.\"Passwords\" as p on p.id_user = u.id " +
                "INNER JOIN public.\"Apps\" as a ON a.id = p.id_app", new PasswordsListMapper());
    }

    @Override
    public List<Password> passwords() {
        return null;
    }

    @Override
    public Password get(int id) {
        return null;
    }

    public void save(Password password) {
        passwords.add(password);
    }
}