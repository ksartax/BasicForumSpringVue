package com.controllers;

import com.configurations.Auth;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RequestMapping(path = "/members")
@Controller
public class UserController {
    private static final String DEFAULT_TEMPLATE = "User/";

    private UsersService usersService;

    @Autowired
    public UserController(
            UsersService usersService
    ) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "")
    public String index() {
        return UserController.DEFAULT_TEMPLATE + "index";
    }

    @RequestMapping(path = "/member/{id}")
    public String member(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", usersService.get(id));

        return UserController.DEFAULT_TEMPLATE + "user";
    }
}
