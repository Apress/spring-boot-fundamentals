package com.apress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private ContactDAO contactDAO;

    @Autowired
    public ContactController(ContactDAO contactDAO){
        this.contactDAO = contactDAO;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index.html");
        model.addObject("contacts", this.contactDAO.findAll());
        return model;
    }
}
