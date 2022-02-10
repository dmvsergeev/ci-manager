package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.News;

import java.util.List;

public interface NewsDAO {

    List<News> news();

    News get(String id);

    String save(News guide);

    List<News> getAll();

}
