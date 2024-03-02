package Converter;


import DataAccessLayer.Dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToJsonConverter implements Converter<String, Dao> {

    private final ObjectMapper objectMapper;

    public StringToJsonConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public Dao convert(String stringData){
        try {
            return objectMapper.readValue(stringData, Dao.class);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Error converting String to JSON", e);
        }
    }

}
