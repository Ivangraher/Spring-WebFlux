package com.webclient.ms_users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClientUser {

    String url = "http://localhost:8082/categories/";

    @Bean
    WebClient client(){
        return WebClient.builder().baseUrl(url).build();
    }
}
