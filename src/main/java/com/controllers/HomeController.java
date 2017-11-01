package com.controllers;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

//    @RequestMapping(path = "/register")
//    public String register()
//    {
//        return this.path + "register";
//    }
//
//    @RequestMapping(path = "/contact")
//    public String contact()
//    {
//        return this.path + "contact";
//    }
//
//    @RequestMapping(path = "/members")
//    public String members()
//    {
//        return this.path + "members";
//    }
//
//    @RequestMapping(path = "/groups")
//    public String groups()
//    {
//        return this.path + "groups";
//    }
}
