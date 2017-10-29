package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController
{
    private String path = "User/";

    @RequestMapping(path = "/member")
    public String member()
    {
        return this.path + "member";
    }
}
