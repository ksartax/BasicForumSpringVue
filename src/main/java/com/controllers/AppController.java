package com.controllers;

import com.dao.UsersDao;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    UsersDao usersDao;

    @RequestMapping(path = "/")
    public User index()
    {
        return usersDao.getAll().get(0);
    }
}
