package com.example.ms_categories.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClientCategories {

    String url = "http://localhost:8080/users/";

    @Bean
    WebClient client(){
        return WebClient.builder().baseUrl(url).build();
    }


}
