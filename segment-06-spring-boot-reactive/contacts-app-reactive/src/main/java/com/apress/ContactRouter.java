package com.apress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ContactRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ContactHandler contactHandler){
        return
                RouterFunctions
                        .route(RequestPredicates.GET("/contacts")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),contactHandler::getContacts )
                        .andRoute(RequestPredicates.GET("/contacts/contains/{name}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), contactHandler::getContactsByName);
    }
}
