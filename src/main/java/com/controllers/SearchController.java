package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/search")
public class SearchController {

    private static final String DEFAULT_TEMPLATE = "Search/";

    @Autowired
    public SearchController(

    ) {

    }

    @RequestMapping(path = "")
    public String profile(ModelMap modelMap) {

        return SearchController.DEFAULT_TEMPLATE + "index";
    }
}
