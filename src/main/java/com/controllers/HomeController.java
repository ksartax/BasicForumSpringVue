package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
@Controller
public class HomeController {
    private String path = "Home/";

    @RequestMapping(path = "/")
    public String index() {
        return this.path + "index";
    }

}
