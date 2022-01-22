package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.pojo.Password;

import java.util.Map;

public class PasswordMapper {

    public Password createPasswordRequest(Map<String, Object> request) {
        try {

            return new Password(
                    Integer.parseInt(String.valueOf(request.get("id_app"))),
                    Integer.parseInt(String.valueOf(request.get("id_user"))),
                    (String) request.get("password"));
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }

    }

}
