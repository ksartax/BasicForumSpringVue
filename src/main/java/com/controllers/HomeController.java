package com.controllers;

import com.models.User;
import com.service.GlobalStatisticsService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@RequestMapping(path = "/")
@Controller
public class HomeController
{
    private String path = "Home/";

    @Autowired
    UsersService usersService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(path = "/")
    public String index()
    {
        return this.path + "index";
    }

    @RequestMapping(path = "/register")
    public String register(ModelMap modelMap)
    {
        modelMap.addAttribute("user", new User());

        return this.path + "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("user") @Valid User user)
    {
        usersService.add(user);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Zarejestrowanych"), 1);

        template.convertAndSend("/user", "");

        return this.path + "register";
    }
}
