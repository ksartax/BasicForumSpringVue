package com.controllers;

import com.configurations.Auth;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {

    private static final String DEFAULT_TEMPLATE = "Profile/";

    private UsersService usersService;

    @Autowired
    public ProfileController(
            UsersService usersService
    ) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "")
    public String profile(ModelMap modelMap) {
        modelMap.addAttribute("user", usersService.getByUsername((new Auth().getLoginUser()).getUsername()));

        return ProfileController.DEFAULT_TEMPLATE + "index";
    }
}
