package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.database.mapper.UserMapper;
import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class UserDatabase implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<User> users;

    @Override
    public List<User> list() {
        return jdbcTemplate.query("SELECT id, username, name, email, password, active FROM public.\"Users\"", new UserMapper());
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public String save(User user) {
        DefaultIdentifier identifier = (DefaultIdentifier) user.getId();
        String idForSearch = identifier.getId();

        List<User> checkUser = jdbcTemplate.query("SELECT id, username, name, email, password, active FROM public.\"Users\" + "
                                                    + "WHERE id = \'" + idForSearch + "\' LIMIT 1", new UserMapper());

        if (!checkUser.isEmpty()) {
            final int result = jdbcTemplate.update("UPDATE public.\"Users\" SET \"username\" = ?, \"password\" = ?,\"name\" = ?, \"email\" = ?, \"active\" = ?"
                                                    + " WHERE \"id\" = ?", user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive(), idForSearch);

            if (result != 0) {
                //roleDAO.save(id, user.getRoles());
                return idForSearch;
            } else {
                throw new DbSaveException("DB save error");
            }
        } else {
            final String id = UUID.randomUUID().toString();
            final int result = jdbcTemplate.update("INSERT INTO public.\"Users\" (\"id\", \"username\",\"password\",\"name\",\"email\",\"active\") " +
                    "VALUES(?,?,?,?,?,?)", id, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive());

            if (result != 0) {
                //roleDAO.save(id, user.getRoles());
                return id;
            } else {
                throw new DbSaveException("DB save error");
            }

        }
    }

}