package com.controllers.Api;

import com.models.Comment;
import com.models.Post;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/post")
public class ApiPostController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private PostsService postsService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Post> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit)
    {
        return this.postsService.getAll(limit);
    }

    @RequestMapping(path = "/{id}/comments", method = RequestMethod.GET)
    public List<Comment> posts(@PathVariable("id") int id)
    {
        return this.commentsService.getAllByPostId(id);
    }

    @RequestMapping(path = "/add/category/{id}", method = RequestMethod.POST)
    public ResponseEntity<Post> add(@RequestBody Post post, @PathVariable("id") int id)
    {
        postsService.add(post, id);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Posty"), 1);

        template.convertAndSend("/category/" + id + "/posts", "");
        template.convertAndSend("/post", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity<Post>(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{postId}/category/{categoryId}")
    public ResponseEntity remove(@PathVariable("postId") int postId, @PathVariable("categoryId") int categoryId)
    {
        this.postsService.remove(postId);
        globalStatisticsService.decrement(globalStatisticsService.getByTitle("Posty"), 1);

        template.convertAndSend("/category/" + categoryId + "/posts", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
