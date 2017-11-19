package com.controllers;

import com.component.Search.SearchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/search")
public class SearchController {

    private static final String DEFAULT_TEMPLATE = "Search/";

    private SearchComponent searchComponent;

    @Autowired
    public SearchController(
            SearchComponent searchComponent
    ) {
        this.searchComponent = searchComponent;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String profile(ModelMap modelMap, @RequestParam("search") String search) {
        modelMap.addAttribute("results", searchComponent.search(search, 10));

        return SearchController.DEFAULT_TEMPLATE + "index";
    }
}
