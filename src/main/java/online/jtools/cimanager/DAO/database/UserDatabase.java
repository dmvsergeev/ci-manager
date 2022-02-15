package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.DAO.database.mapper.UserMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDatabase implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private List<User> users;

    @Override
    public List<User> list() {
        return jdbcTemplate.query("SELECT id, username, name, email, password, active FROM public.\"Users\"", new UserMapper());
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public Identifier save(User user) {
        final int result;
        final DefaultIdentifier identifier;
        if (user.isNew()) {
            identifier = DefaultIdentifier.generateId();
            result = jdbcTemplate.update("INSERT INTO public.\"Users\" (\"id\", \"username\",\"password\",\"name\",\"email\",\"active\") " +
                    "VALUES(?,?,?,?,?,?)", identifier.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive());
        } else {
            identifier = (DefaultIdentifier) user.getId();
            result = jdbcTemplate.update("UPDATE public.\"Users\" SET \"username\" = ?, \"password\" = ?,\"name\" = ?, \"email\" = ?, \"active\" = ?"
                    + " WHERE \"id\" = ?", user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive(), identifier.getId());
        }

        if (result != 0) {
            return identifier;
        } else {
            throw new DbSaveException("DB save error");
        }

    }

}