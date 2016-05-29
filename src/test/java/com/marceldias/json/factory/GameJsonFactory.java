package com.marceldias.json.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.mapper.factory.Jackson2ObjectMapperFactory;

public class GameJsonFactory implements Jackson2ObjectMapperFactory {
    @Override
    public ObjectMapper create(Class aClass, String s) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return mapper;
    }
}
