package online.jtools.cimanager.models.api;

import online.jtools.cimanager.models.pojo.CimanagerDataValidationException;

public abstract class Model {
    protected <T> void assertField(String name, T value, T test) {
        if (value == test) throw new CimanagerDataValidationException(name, value);
    }
}
