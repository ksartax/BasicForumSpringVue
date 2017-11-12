package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/contact")
@Controller
public class ContactController {
    private static final String DEFAULT_TEMPLATE = "Contact/";

    @RequestMapping(path = "")
    public String index() {
       return ContactController.DEFAULT_TEMPLATE + "index";
    }
}
