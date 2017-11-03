package com.controllers;

import com.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController
{
    private String path = "Home/";

    @RequestMapping(path = "/")
    public String index()
    {
        return this.path + "index";
    }

    @RequestMapping(path = "/register")
    public String register()
    {
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
