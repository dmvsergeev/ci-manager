package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.News;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new News(new DefaultIdentifier(rs.getString("id_news")),
                rs.getString("title"),
                rs.getString("content"));
    }
}
