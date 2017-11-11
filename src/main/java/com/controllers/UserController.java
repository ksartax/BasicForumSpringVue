package com.controllers;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/members")
@Controller
public class UserController {
    @Autowired
    UsersService usersService;
    private String path = "User/";

    @RequestMapping(path = "")
    public String index() {
        return this.path + "index";
    }

    @RequestMapping(path = "/member/{id}")
    public String member(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", usersService.get(id));

        return this.path + "user";
    }
}
