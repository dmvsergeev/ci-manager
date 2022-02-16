package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRestMapper {

    @NotNull
    public User createUserRequest(@NotNull Map<String, Object> request) {
        try {
            if (Objects.equals(request.get("id_user").toString(), "0")) {
                return new User(DefaultIdentifier.generateId(),
                        (String) request.get("name"),
                        (String) request.get("email"),
                        (String) request.get("username"));
            } else {
                return new User(new DefaultIdentifier(request.get("id_user").toString()),
                        (String) request.get("name"),
                        (String) request.get("email"),
                        (String) request.get("username"));
            }
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }
    }

    @NotNull
    public Map<String, String> createUserResponse(@NotNull User user) {
        return Map.of(
                "id", user.getId().toString(),
                "name", user.getName(),
                "username", user.getUsername(),
                "email", user.getEmail());
    }
    @NotNull
    public List<Map<String, String>> createUserResponse(@NotNull List<User> user) {
        return user.stream().map(this::createUserResponse).collect(Collectors.toList());
    }

}
