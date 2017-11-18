package com.controllers;

import com.component.mail.MailComponent;
import com.component.mail.MailContext;
import com.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@RequestMapping(path = "/contact")
@Controller
public class ContactController {
    private static final String DEFAULT_TEMPLATE = "Contact/";

    private MailComponent mailService;

    @Autowired
    public ContactController(
            MailComponent mailService
    ) {
        this.mailService = mailService;
    }

    @RequestMapping(path = "")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("contact", new Contact());

        return ContactController.DEFAULT_TEMPLATE + "index";
    }

    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public String sendEmail(final Contact contact) {
        try {

            mailService.sendMail(contact.getTitle(), new MailContext() {
                public Context setContext() {
                    Context context = new Context();
                    context.setVariable("description", contact.getDescription());
                    context.setVariable("firstname", contact.getFirstName());
                    context.setVariable("email", contact.getEmail());
                    context.setVariable("title", contact.getTitle());

                    return context;
                }
            });

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return ContactController.DEFAULT_TEMPLATE + "success";
    }
}
