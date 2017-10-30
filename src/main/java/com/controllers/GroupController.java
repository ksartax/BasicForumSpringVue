package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/groups")
public class GroupController
{
    private String path = "Group/";

    @RequestMapping(path = "")
    public String index()
    {
        return this.path + "index";
    }
}
