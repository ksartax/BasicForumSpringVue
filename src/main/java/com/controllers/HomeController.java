package com.controllers;

import com.models.User;
import com.service.GlobalStatisticsService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class HomeController
{
    private String path = "Home/";

    @Autowired
    UsersService usersService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

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

        return this.path + "register";
    }

    @RequestMapping(path = "/contact")
    public String contact()
    {
        return this.path + "contact";
    }

    @RequestMapping(path = "/members")
    public String members()
    {
        return this.path + "members";
    }
}
