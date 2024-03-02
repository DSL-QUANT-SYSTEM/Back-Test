package handler.SystemConfigHandler;

import Converter.JsonToStringConverter;
import Converter.StringToJsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void AddFormatters(FormatterRegistry registry){
        registry.addConverter(new StringToJsonConverter(ObjectMapper()));
        registry.addConverter(new JsonToStringConverter(ObjectMapper()));
    }

    @Bean
    public ObjectMapper ObjectMapper(){
        return new ObjectMapper();
    }
}
