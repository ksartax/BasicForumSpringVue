package com.controllers;

import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController
{
    private String path = "User/";

    @Autowired
    UsersService usersService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    FriendsService friendsService;

    @RequestMapping(path = "/member/{id}")
    public String member(@PathVariable("id") int id, ModelMap modelMap)
    {
        modelMap.addAttribute("user", usersService.get(id));
        modelMap.addAttribute("comments", commentsService.getAllByUserId(id));
        modelMap.addAttribute("friends", friendsService.getAllByUser(id));

        return this.path + "user";
    }
}
