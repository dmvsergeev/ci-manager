package online.jtools.cimanager.DAO;

import online.jtools.cimanager.models.pojo.Password;
import online.jtools.cimanager.models.pojo.PasswordsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    public List<Password> index() {
        return jdbcTemplate.query("SELECT * FROM public.\"Passwords\"", new PasswordMapper());
    }

    public List<PasswordsList> getForUser() {
        return jdbcTemplate.query("SELECT " +
                "u.user_id, u.user_name,..." +
                "p.id, p.password, a.name as app " +
                "FROM public.\"Users\" as u " +
                "INNER JOIN public.\"Passwords\" as p on p.userId = u.id " +
                "INNER JOIN public.\"Apps\" as a ON a.id = p.id_app", new PasswordsListMapper());
    }

    public Password show(int id) {
        return passwords.stream().filter(password -> password.getId() == id).findAny().orElse(null);
    }

    public void save(Password password) {
        password.setId(++USERS_COUNT);
        passwords.add(password);
    }
}