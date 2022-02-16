package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppRestMapper {

    @NotNull
    public App createAppRequest(@NotNull Map<String, Object> request) {
        try {
            return new App(DefaultIdentifier.generateId(), (String) request.get("name"), (String) request.get("url"));
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }
    }

    @NotNull
    public Map<String, String> createAppResponse(@NotNull App app) {
        return Map.of("id", app.getId().toString(),
                "name", app.getName(),
                "url", app.getUrl());
    }

    @NotNull
    public List<Map<String, String>> createAppResponse(@NotNull List<App> apps) {
        return apps.stream().map(this::createAppResponse).collect(Collectors.toList());
    }

}
