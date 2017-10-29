package com.controllers;

import org.springframework.stereotype.Controller;
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
}
