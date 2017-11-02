package com.controllers.Api;

import com.models.Comment;
import com.models.Post;
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
@RequestMapping(path = "/api/post")
public class ApiPostController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private PostsService postsService;
    @Autowired
    private CommentsService commentsService;

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
}
