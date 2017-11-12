package com.controllers.Api;

import com.models.Comment;
import com.models.Post;
import com.service.GlobalStatisticsService;
import com.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/post")
public class ApiPostController {
    private SimpMessagingTemplate template;
    private PostsService postsService;
    private GlobalStatisticsService globalStatisticsService;

    @Autowired
    public ApiPostController(
            SimpMessagingTemplate template,
            PostsService postsService,
            GlobalStatisticsService globalStatisticsService
    ) {
        this.template = template;
        this.postsService = postsService;
        this.globalStatisticsService = globalStatisticsService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Post> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit) {
        return this.postsService.getAll(limit);
    }

    @RequestMapping(path = "/{id}/comments", method = RequestMethod.GET)
    public Set<Comment> posts(@PathVariable("id") int id) {
        return this.postsService.get(id).getComments();
    }

    @RequestMapping(path = "/add/category/{id}", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Post post, @PathVariable("id") int id) {
        postsService.add(post, id);
        globalStatisticsService.incrementPosts(1);

        template.convertAndSend("/category/" + id + "/posts", "");
        template.convertAndSend("/post", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{postId}/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("postId") int postId, @PathVariable("categoryId") int categoryId) {
        postsService.remove(postId);
        globalStatisticsService.decrementPosts(postId, 1);

        template.convertAndSend("/category/" + categoryId + "/posts", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
