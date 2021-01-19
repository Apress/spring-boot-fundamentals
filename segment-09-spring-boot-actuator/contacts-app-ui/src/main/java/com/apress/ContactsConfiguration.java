package com.apress;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties({ContactsProperties.class})
@Configuration
public class ContactsConfiguration {

    @Bean
    public RestTemplate restTemplate(ContactsProperties contactsProperties){
        return new RestTemplateBuilder().basicAuthentication(contactsProperties.getUsername(), contactsProperties.getPassword()).build();
    }
}
