package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contact")
@Controller
public class ContactController {
    private String path = "Contact/";

    @RequestMapping(path = "")
    public String index() {
        return this.path + "index";
    }
}
