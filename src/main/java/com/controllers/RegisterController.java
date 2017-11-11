package com.controllers;

import com.models.User;
import com.service.GlobalStatisticsService;
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

    private String path = "Register/";

    private UsersService usersService;
    private GlobalStatisticsService globalStatisticsService;
    private SimpMessagingTemplate template;

    @Autowired
    public RegisterController(
            UsersService usersService,
            GlobalStatisticsService globalStatisticsService,
            SimpMessagingTemplate template
    ) {
        this.usersService = usersService;
        this.globalStatisticsService = globalStatisticsService;
        this.template = template;
    }

    @RequestMapping(path = "")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());

        return this.path + "index";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String registerPost(@Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            return this.path + "index";
        }

        usersService.add(user);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Zarejestrowanych"), 1);

        template.convertAndSend("/user", "");

        return this.path + "index";
    }
}
