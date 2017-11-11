package com.controllers.Api;

import com.models.Category;
import com.models.Post;
import com.service.CategoriesService;
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
public class ApiCategoryController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Category> index() {
        return this.categoriesService.getAll();
    }

    @RequestMapping(path = "/{id}/posts", method = RequestMethod.GET)
    public List<Post> posts(@PathVariable("id") int id) {
        return this.postsService.getAllByCategoryId(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Category category) {
        categoriesService.add(category);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Kategorie"), 1);

        template.convertAndSend("/category/level/0", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("categoryId") int categoryId) {
        this.categoriesService.remove(categoryId);
        globalStatisticsService.decrement(globalStatisticsService.getByTitle("Kategorie"), 1);

        template.convertAndSend("/category/level/0", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
