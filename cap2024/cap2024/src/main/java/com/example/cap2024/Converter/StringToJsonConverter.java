package com.example.cap2024.Converter;


import com.example.cap2024.DataAccessLayer.Dao_history;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToJsonConverter implements Converter<String, Dao_history> {

    private final ObjectMapper objectMapper;

    public StringToJsonConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public Dao_history convert(String stringData){
        try {
            return objectMapper.readValue(stringData, Dao_history.class);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error converting String to JSON", e);
        }
    }

}
