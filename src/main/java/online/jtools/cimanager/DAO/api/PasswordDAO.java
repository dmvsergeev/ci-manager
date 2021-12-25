package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.pojo.Password;

import java.util.List;

public interface PasswordDAO {
    List<Password> passwords();

    Password get(int id);

    void save(Password password);
}
