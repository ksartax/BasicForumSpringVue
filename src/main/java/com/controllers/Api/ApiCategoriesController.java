package com.controllers.Api;

import com.models.Category;
import com.models.Comment;
import com.models.Post;
import com.service.CategoriesService;
import com.service.CommentsService;
import com.service.GlobalStatisticsService;
import com.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

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

    @RequestMapping(path = "/basics", method = RequestMethod.GET)
    public List<Category> basics()
    {
        return this.categoriesService.getByLevel(Category.BASIC);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<Category> add(@RequestBody Category category)
    {
        categoriesService.add(category);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Kategorie"), 1);

        template.convertAndSend("/category/level/0", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity<Category>(HttpStatus.OK);
    }
}
