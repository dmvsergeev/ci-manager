package online.jtools.cimanager.DAO;


import online.jtools.cimanager.models.pojo.App;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppMapper implements RowMapper<App> {

    @Override
    public App mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        App app = new App();

        app.setId(resultSet.getInt("id"));
        app.setName(resultSet.getString("name"));
        app.setUrl(resultSet.getString("url"));

        return app;
    }
}
