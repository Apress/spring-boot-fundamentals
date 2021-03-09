package com.apress;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ContactRepository extends ReactiveMongoRepository<Contact,String> {

    Flux<Contact> findByNameContainsIgnoreCase(String name);
}
