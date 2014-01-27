package org.xaxox.config.spring;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.xaxox")
@PropertySource("classpath*:application.propertiesw")
public class BeansRepo {

    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
