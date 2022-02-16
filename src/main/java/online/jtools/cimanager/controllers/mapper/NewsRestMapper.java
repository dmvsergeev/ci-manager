package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.models.pojo.News;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NewsRestMapper {
    @NotNull
    public List<Map<String, String>> createNewsResponse(@NotNull List<News> news) {
        return news.stream().map(this::createNewsResponse).collect(Collectors.toList());
    }

    @NotNull
    public Map<String, String> createNewsResponse(@NotNull News news) {
        return Map.of("id", news.getId().toString(),
                "title", news.getTitle(),
                "content", news.getContent());
    }
}
