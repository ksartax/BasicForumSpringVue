package com.controllers;

import com.service.CategoriesService;
import com.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/forum")
public class ForumController {
    private static final String DEFAULT_TEMPLATE = "Forum/";

    private CategoriesService categoriesService;
    private PostsService postsService;

    @Autowired
    public ForumController(
            CategoriesService categoriesService,
            PostsService postsService
    ) {
        this.categoriesService = categoriesService;
        this.postsService = postsService;
    }

    @RequestMapping(path = "")
    public String index() {
        return ForumController.DEFAULT_TEMPLATE + "index";
    }

    @RequestMapping(path = "/category/{id}")
    public String category(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("category", categoriesService.get(id));

        return ForumController.DEFAULT_TEMPLATE + "category";
    }

    @RequestMapping(path = "/post/{id}")
    public String post(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("post", postsService.get(id));

        return ForumController.DEFAULT_TEMPLATE + "post";
    }
}
