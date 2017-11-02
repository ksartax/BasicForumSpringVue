package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/group")
public class GroupController
{
    private String path = "Group/";

    @RequestMapping("/{id}")
    public String group(@PathVariable("id") int id)
    {
        return path + "group";
    }
}
