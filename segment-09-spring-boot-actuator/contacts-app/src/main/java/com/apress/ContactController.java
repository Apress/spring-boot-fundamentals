package com.apress;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ContactController {

    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public ResponseEntity<Iterable<Contact>> getContacts(){
        return ResponseEntity.ok(contactRepository.findAll());
    }

    @GetMapping("/contacts/contains/{name}")
    public ResponseEntity<Iterable<Contact>> getContactsByNameContains(@PathVariable String name){
        return ResponseEntity.ok(contactRepository.findByNameContainsIgnoreCase(name));
    }
}
