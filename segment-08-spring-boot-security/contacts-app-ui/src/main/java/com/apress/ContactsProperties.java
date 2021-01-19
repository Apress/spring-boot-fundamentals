package com.apress;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "contacts")
public class ContactsProperties {
    private String server = "http://localhost:8080/contacts";
    private String username = "apress";
    private String password = "springboot";
}
