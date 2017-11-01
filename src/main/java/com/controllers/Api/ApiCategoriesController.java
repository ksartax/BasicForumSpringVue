package com.controllers.Api;

import com.models.Category;
import com.models.Comment;
import com.service.CategoriesService;
import com.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class ApiCategoriesController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add()
    {
        Category category = new Category();
        this.template.convertAndSend("/category", category);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Category> index()
    {
        return this.categoriesService.getAll();
    }
}
