package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.pojo.App;

import java.util.List;

public interface AppDAO {
    List<App> apps();

    App get(int id);

    void save(App app);
}
