package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.pojo.Password;
import online.jtools.cimanager.models.pojo.PasswordsList;

import java.util.List;

public interface PasswordDAO {
    List<Password> passwords();

    Password get(int id);

    String save(Password password);

    List<PasswordsList> getForUser(String username);
}
