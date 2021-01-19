package com.apress;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Log4j2
@Configuration
public class ContactInit {

    @Bean
    CommandLineRunner init(ContactRepository contactRepository){
        return args -> {
            contactRepository
                    .deleteAll()
                    .thenMany(
                            Flux
                                    .just(
                                        new Contact("mike@email.com","Mike","1-800-CONTACTS"),
                                        new Contact("mark@email.com","Mark","1-800-CONTACTS"),
                                        new Contact("matt@email.com","Matt","1-800-CONTACTS"))
                                    .flatMap(contactRepository::save)
                    )
                    .thenMany(contactRepository.findAll())
                    .subscribe(contact -> log.info("Saving {}",contact));

        };
    }

}
