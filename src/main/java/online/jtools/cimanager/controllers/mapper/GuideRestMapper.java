package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.models.pojo.Guide;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuideRestMapper {
    @NotNull
    public List<Map<String, String>> createGuideResponse(@NotNull List<Guide> guides) {
        return guides.stream().map(this::createGuideResponse).collect(Collectors.toList());
    }

    @NotNull
    public Map<String, String> createGuideResponse(@NotNull Guide guide) {
        return Map.of("id", guide.getId().toString(),
                "title", guide.getTitle(),
                "content", guide.getContent());
    }
}
