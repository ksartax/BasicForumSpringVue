package com.controllers.Api;

import com.models.Comment;
import com.models.Post;
import com.models.User;
import com.service.CommentsService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
public class ApiCommentController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Comment> index()
    {
        return this.commentsService.getAll();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Comment comment)
    {
        User user = usersService.get(1);
        comment.setUser(user);
        Post post = new Post();
        post.setId(1);
        comment.setPost(post);
        System.out.println(comment.getDescription());
        commentsService.add(comment);

        template.convertAndSend("/post/1/comments", comment);

        return new ResponseEntity(comment,HttpStatus.OK);
    }
}
