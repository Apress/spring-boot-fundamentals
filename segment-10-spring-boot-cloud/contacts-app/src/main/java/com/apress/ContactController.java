package com.apress;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @GetMapping("/contacts/{email}")
    public ResponseEntity<Contact> getContact(@PathVariable String email){
        return ResponseEntity.ok(contactRepository.findById(email).orElseThrow(() -> new RuntimeException("Contact not found!")));
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        URI location = URI.create(String.format("/contacts/%s", contact.getEmail()));
        return ResponseEntity.created(location).body(contactRepository.save(contact));
    }
}
