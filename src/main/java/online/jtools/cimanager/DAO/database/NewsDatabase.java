package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.NewsDAO;
import online.jtools.cimanager.DAO.database.mapper.NewsMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsDatabase implements NewsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<News> news;

    public List<News> index() { return jdbcTemplate.query("SELECT * FROM public.\"News\"", new NewsMapper()); }

    public List<News> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.title, a.content, a.id as id_news " +
                "FROM public.\"News\" as a ", new NewsMapper());
    }

    @Override
    public List<News> news() {
        return null;
    }

    @Override
    public News get(String id) {

        return jdbcTemplate.queryForObject("SELECT " +
                "a.title, a.content, a.id as id_news " +
                "FROM public.\"News\" as a " +
                "WHERE a.id = ?", new Object[]{ id }, new NewsMapper());

    }

    @Override
    public String save(News news) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"News\" (\"title\",\"content\") " +
                "VALUES(?,?)", news.getTitle(), news.getContent());
        if (result != 0) {
            return "";
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}