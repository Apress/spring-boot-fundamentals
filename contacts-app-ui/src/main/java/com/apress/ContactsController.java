package com.apress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactsController {

    RestTemplate restTemplate = new RestTemplate();
    String server;

    public ContactsController(@Value("${contacts.server}") String server){
        this.server = server;
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index.html");
        model.addObject("contacts", restTemplate.getForEntity(server,Contact[].class).getBody());
        return model;
    }
}
