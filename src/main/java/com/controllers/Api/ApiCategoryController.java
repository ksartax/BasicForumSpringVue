package com.controllers.Api;

import com.dvo.CategoryView;
import com.dvo.PostView;
import com.models.Category;
import com.service.CategoriesService;
import com.service.GlobalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class ApiCategoryController {

    private SimpMessagingTemplate template;
    private CategoriesService categoriesService;
    private GlobalStatisticsService globalStatisticsService;

    @Autowired
    public ApiCategoryController(
            SimpMessagingTemplate template,
            CategoriesService categoriesService,
            GlobalStatisticsService globalStatisticsService
    ) {
        this.categoriesService = categoriesService;
        this.template = template;
        this.globalStatisticsService = globalStatisticsService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CategoryView> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit) {
        return this.categoriesService.getAll(limit);
    }

    @RequestMapping(path = "/{id}/posts", method = RequestMethod.GET)
    public List<PostView> posts(@PathVariable("id") int id) {
        return this.categoriesService.getPostsByCategoryId(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Category category) {
        globalStatisticsService.incrementCategory(1);
        categoriesService.add(category);

        template.convertAndSend("/category/level/0", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("categoryId") int categoryId) {
        globalStatisticsService.decrementCategory(categoryId, 1);
        categoriesService.remove(categoryId);

        template.convertAndSend("/category/level/0", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
