package online.jtools.cimanager.DAO;

import online.jtools.cimanager.models.Password;
import online.jtools.cimanager.models.PasswordsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import online.jtools.cimanager.models.User;

import java.util.List;

@Component
public class PasswordDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PasswordDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int USERS_COUNT;

    private List<Password> passwords;

    public List<Password> index() { return jdbcTemplate.query("SELECT * FROM public.\"Passwords\"", new PasswordMapper()); }

    public List<PasswordsList> getForUser() { return jdbcTemplate.query("SELECT p.id, p.password, a.name as app FROM public.\"Passwords\" as p LEFT JOIN public.\"Apps\" as a ON (a.id = p.id_app)", new PasswordsListMapper()); }

    public Password show(int id) {
        return passwords.stream().filter(password -> password.getId() == id).findAny().orElse(null);
    }

    public void save(Password password) {
        password.setId(++USERS_COUNT);
        passwords.add(password);
    }
}