package com.controllers.Api;

import com.dvo.CommentView;
import com.dvo.PostView;
import com.models.Post;
import com.service.GlobalStatisticsService;
import com.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PostView> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit) {
        return this.postsService.getAll(limit);
    }

    @RequestMapping(path = "/{id}/comments", method = RequestMethod.GET)
    public List<CommentView> posts(@PathVariable("id") int id) {
        return this.postsService.getCommentByPostId(id);
    }

    @RequestMapping(path = "/add/category/{id}", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Post post, @PathVariable("id") int id) {
        globalStatisticsService.incrementPosts(1);
        postsService.add(post, id);

        template.convertAndSend("/category/" + id + "/posts", "");
        template.convertAndSend("/post", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{postId}/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("postId") int postId, @PathVariable("categoryId") int categoryId) {
        globalStatisticsService.decrementPosts(postId, 1);
        postsService.remove(postId);

        template.convertAndSend("/category/" + categoryId + "/posts", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
