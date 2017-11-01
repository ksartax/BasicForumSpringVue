package com.controllers.Api;

import com.models.Post;
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

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add() {
        Post post = new Post();

        this.template.convertAndSend("/post", post);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Post> index()
    {
        return this.postsService.getAll();
    }
}
