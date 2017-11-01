package com.controllers.Api;

import com.models.Category;
import com.models.Comment;
import com.models.Post;
import com.service.CategoriesService;
import com.service.CommentsService;
import com.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private PostsService postsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Category> index()
    {
        return this.categoriesService.getAll();
    }

    @RequestMapping(path = "/{id}/posts", method = RequestMethod.GET)
    public List<Post> posts(@PathVariable("id") int id)
    {
        return this.postsService.getAllByCategoryId(id);
    }

    @RequestMapping(path = "/generals", method = RequestMethod.GET)
    public List<Category> generals()
    {
        return this.categoriesService.getByLevel(1);
    }

    @RequestMapping(path = "/basics", method = RequestMethod.GET)
    public List<Category> basics()
    {
        return this.categoriesService.getByLevel(0);
    }
}
