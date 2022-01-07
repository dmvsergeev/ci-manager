package online.jtools.cimanager.controllers.mapper;

import online.jtools.cimanager.controllers.validator.exception.InvalidDataTypeException;
import online.jtools.cimanager.models.pojo.App;

import java.util.Map;

public class AppMapper {

    public App createAppRequest(Map<String, Object> request) {
        try {

            return new App((String) request.get("name"), (String) request.get("url"));
        } catch (RuntimeException e) {
            throw new InvalidDataTypeException("Invalid Data Format " + e.getMessage());
        }

    }

}
