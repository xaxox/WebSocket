package org.xaxox.config.spring;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.xaxox"})
@PropertySource(value = "classpath:application.properties")
public class BeansRepo {

    @Autowired
    private ApplicationContext context;

    @Bean
    public Gson getGson(){
        return new Gson();
    }


}
