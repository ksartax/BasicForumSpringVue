package com.controllers;

import com.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @Autowired
    UsersDao usersDao;

    @RequestMapping(path = "/")
    public String index()
    {
        return "Home/index";
    }
}
