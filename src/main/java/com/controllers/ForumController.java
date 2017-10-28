package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/forum")
public class ForumController
{
    private String path = "Forum/";

    @RequestMapping(path = "")
    public String index()
    {
        return this.path + "index";
    }

    @RequestMapping(path = "/category")
    public String category()
    {
        return this.path + "category";
    }

    @RequestMapping(path = "/topic")
    public String topic()
    {
        return this.path + "topic";
    }
}
