package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.NewsDAO;
import online.jtools.cimanager.DAO.database.exception.DBDataCorruption;
import online.jtools.cimanager.DAO.database.exception.NotFoundException;
import online.jtools.cimanager.DAO.database.mapper.NewsDatabaseMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.News;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsDatabase implements NewsDAO {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDatabase(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    @Override
    public List<News> getAll() {
        return jdbcTemplate.query("SELECT " +
                "a.id as \"id_news\", " +
                "a.title as \"news_title\", " +
                "a.content as \"news_content\" " +
                "FROM public.\"News\" as a ", new NewsDatabaseMapper());
    }

    @NotNull
    @Override
    public List<News> news() {
        return null;
    }

    @NotNull
    @Override
    public News get(@NotNull Identifier id) {
        List<News> news = jdbcTemplate.query("SELECT " +
                "a.id as \"id_news\", " +
                "a.title as \"news_title\", " +
                "a.content as \"news_content\" " +
                "FROM public.\"News\" as a " +
                "WHERE a.id = ?", new NewsDatabaseMapper(), id.toString());
        if (news.isEmpty()) {
            throw new NotFoundException("News " + id + " was not found");
        } else if (news.size() > 1) {
            throw new DBDataCorruption("DB news ID " + id + " is duplicated or corrupted");
        } else {
            return news.get(0);
        }
    }

    @NotNull
    @Override
    public News save(@NotNull News news) {
        final int result = jdbcTemplate.update("INSERT INTO public.\"News\" (\"id\", \"title\", \"content\") " +
                "VALUES(?,?,?)", news.getId().toString(), news.getTitle(), news.getContent());
        if (result != 0) {
            return new News(new DefaultIdentifier(news.getId().toString()), news.getTitle(), news.getContent());
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}