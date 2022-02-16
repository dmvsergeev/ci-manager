package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.News;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDatabaseMapper implements RowMapper<News> {

    @Override
    @NotNull
    public News mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new News(new DefaultIdentifier(rs.getString("id_news")),
                rs.getString("news_title"),
                rs.getString("news_content"));
    }
}
