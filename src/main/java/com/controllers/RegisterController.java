package com.controllers;

import com.models.User;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping(path = "/register")
@Controller
public class RegisterController {
    private static final String DEFAULT_TEMPLATE = "Register/";

    private UsersService usersService;
    private SimpMessagingTemplate template;

    @Autowired
    public RegisterController(
            UsersService usersService,
            SimpMessagingTemplate template
    ) {
        this.usersService = usersService;
        this.template = template;
    }

    @RequestMapping(path = "")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());

        return RegisterController.DEFAULT_TEMPLATE + "index";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String registerPost(@Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            return RegisterController.DEFAULT_TEMPLATE + "index";
        }

        usersService.add(user);
        template.convertAndSend("/user", "");

        return RegisterController.DEFAULT_TEMPLATE + "index";
    }
}
