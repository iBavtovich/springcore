package com.epam.spring.core.spring;

import com.epam.spring.core.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Client client() {
        Client client = new Client();
        client.setId(environment.getRequiredProperty("id"));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }

}
