package online.jtools.cimanager.DAO.api;

import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.Guide;

import java.util.List;

public interface GuideDAO {

    List<Guide> guides();

    Guide get(String id);

    String save(Guide guide);

    List<Guide> getAll();

}
