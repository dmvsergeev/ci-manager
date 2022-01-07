package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.User;

import java.util.Map;
import java.util.Objects;

public class UserMapper {

    public User createUserRequest(Map<String, Object> request) {
        try {
            if (!Objects.equals(request.get("id_user").toString(), "0")) {
                return new User(new DefaultIdentifier(request.get("id_user").toString()),(String) request.get("name"), (String) request.get("email"), (String) request.get("username"));
            } else {
                return new User((String) request.get("name"), (String) request.get("email"), (String) request.get("username"));
            }
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }

    }

}
