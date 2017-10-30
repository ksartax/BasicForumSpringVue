package com.controllers;

import com.models.User;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class te {
    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public List<User> test()
    {
        List<User> a = usersService.getAll();

        return a;
    }
}
