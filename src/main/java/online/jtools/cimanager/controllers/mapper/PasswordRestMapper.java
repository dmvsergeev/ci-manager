package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.App;
import online.jtools.cimanager.models.pojo.Password;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PasswordRestMapper {

    @NotNull
    public Password createPasswordRequest(@NotNull Map<String, Object> request) {
        try {
            final DefaultIdentifier appId = new DefaultIdentifier(String.valueOf(request.get("id_app")));
            final DefaultIdentifier userId = new DefaultIdentifier(String.valueOf(request.get("id_user")));
            return new Password(App.id(appId), User.id(userId), (String) request.get("password"));
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }
    }

    @NotNull
    public Map<String, Object> createPasswordResponse(@NotNull Password password) {
        final Map<String, String> app = new AppRestMapper().createAppResponse(password.getApp());
        final Map<String, String> user = new UserRestMapper().createUserResponse(password.getUser());

        return Map.of("app", app,
                "user", user,
                "password", password.getPassword());
    }

    @NotNull
    public List<Map<String, Object>> createPasswordResponse(@NotNull List<Password> passwords) {
        return passwords.stream().map(this::createPasswordResponse).collect(Collectors.toList());
    }
}
