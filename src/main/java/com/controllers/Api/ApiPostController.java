package com.controllers.Api;

import com.configurations.Auth;
import com.models.Category;
import com.models.Comment;
import com.models.Post;
import com.models.User;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CategoriesService categoriesService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Post> index()
    {
        return this.postsService.getAll();
    }

    @RequestMapping(path = "/{id}/comments")
    public List<Comment> posts(@PathVariable("id") int id)
    {
        return this.commentsService.getAllByPostId(id);
    }

    @RequestMapping(path = "/add/category/{id}", method = RequestMethod.POST)
    public ResponseEntity<Post> add(@RequestBody Post post, @PathVariable("id") int id)
    {
        postsService.add(post, id);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Posty"), 1);

       // template.convertAndSend("/category/posts", post);
       // template.convertAndSend("/globalStatistic");

        return new ResponseEntity<Post>(HttpStatus.OK);
    }
}
