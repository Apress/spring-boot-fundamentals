package com.apress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {

    @Bean
    public CommandLineRunner init(ContactRepository contactRepository){
        return args -> {
            contactRepository.save(new Contact("admin","Administrator","1-800-CONTACTS","admin","ADMIN,ACTUATOR",true));
        };
    }
}
