package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
@Controller
public class HomeController {
    private static final String DEFAULT_TEMPLATE = "Home/";

    @RequestMapping(path = "/")
    public String index() {
        return HomeController.DEFAULT_TEMPLATE + "index";
    }
}
