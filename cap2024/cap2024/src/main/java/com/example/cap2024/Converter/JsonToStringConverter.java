package com.example.cap2024.Converter;

import com.example.cap2024.DataAccessLayer.Dao_history;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;

public class JsonToStringConverter implements Converter<Dao_history, String> {

    private final ObjectMapper objectMapper;

    public JsonToStringConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public String convert(Dao_history JsonData){
        try{
            return objectMapper.writeValueAsString(JsonData);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error converting JSON to String", e);
        }
    }
}
