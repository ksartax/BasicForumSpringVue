package com.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(path = "/")
@Controller
public class HomeController {
    private static final String DEFAULT_TEMPLATE = "Home/";

    @RequestMapping(path = "/")
    public String index() {
        return HomeController.DEFAULT_TEMPLATE + "index";
    }

    @RequestMapping(path = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return HomeController.DEFAULT_TEMPLATE + "index";
    }
}
