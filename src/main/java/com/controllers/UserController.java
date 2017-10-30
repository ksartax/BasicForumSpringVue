package com.controllers;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController
{
    @Autowired
    private UsersService usersService;

    private String path = "User/";

    @RequestMapping(path = "/member")
    public String member()
    {
        return this.path + "member";
    }
}
