package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.App;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppMapper implements RowMapper<App> {

    @Override
    public App mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        App app = new App();

        app.setId(resultSet.getInt("id_app"));
        app.setName(resultSet.getString("name"));
        app.setUrl(resultSet.getString("url"));

        return app;
    }
}
