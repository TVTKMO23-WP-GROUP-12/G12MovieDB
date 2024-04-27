package com.group12.moviedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Configuration
@PropertySource("classpath:database.properties")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MoviedbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviedbApplication.class, args);
    }

}

