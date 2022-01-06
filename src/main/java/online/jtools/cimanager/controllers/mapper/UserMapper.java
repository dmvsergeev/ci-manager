package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.User;

import java.util.Map;

public class UserMapper {

    public User createUserRequest(Map<String, Object> request) {

        try {
            return new User(new DefaultIdentifier(Integer.parseInt((String) request.get("id"))), (String) request.get("name"), (String) request.get("email"), (String) request.get("username"));
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }

    }

}
