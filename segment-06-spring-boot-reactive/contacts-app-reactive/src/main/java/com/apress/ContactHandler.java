package com.apress;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class ContactHandler {

    private ContactRepository contactRepository;

    public Mono<ServerResponse> getContacts(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(contactRepository.findAll(), Contact.class);
    }

    public Mono<ServerResponse> getContactsByName(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(contactRepository.findByNameContainsIgnoreCase(request.pathVariable("name")),Contact.class);
    }
}
