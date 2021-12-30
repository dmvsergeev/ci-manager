package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.pojo.User;

import java.util.List;

public interface UserDAO {
    List<User> list();

    User get(int id);

    boolean save(User user);
}
