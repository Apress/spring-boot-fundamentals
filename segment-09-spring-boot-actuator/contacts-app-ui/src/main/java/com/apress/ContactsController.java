package com.apress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactsController {

    RestTemplate restTemplate;
    ContactsProperties contactsProperties;

    public ContactsController(ContactsProperties contactsProperties, RestTemplate restTemplate){
        this.contactsProperties = contactsProperties;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("contacts", restTemplate.getForEntity(contactsProperties.getServer(),Contact[].class).getBody());
        model.addObject("contactForm", new Contact());
        return model;
    }

    @PostMapping("/contact")
    public String indexByName(@ModelAttribute("contactForm") Contact contact, Model model){
        ResponseEntity<Contact[]> responseEntity = restTemplate.getForEntity(contactsProperties.getServer() + "/contains/" + contact.getName(),Contact[].class);
        model.addAttribute("contacts", responseEntity.getBody());
        model.addAttribute("contactForm", new Contact());
        return "index";
    }
}
