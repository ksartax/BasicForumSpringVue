package com.controllers.Api;

import com.models.Comment;
import com.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
public class ApiCommentController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private CommentsService commentsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Comment> index()
    {
        return this.commentsService.getAll();
    }

    @RequestMapping(path = "/add/post/{id}", method = RequestMethod.POST)
    public ResponseEntity<Comment> add(@RequestBody Comment comment, @PathVariable("id") int id)
    {
          commentsService.add(comment, id);

//        template.convertAndSend("/post/1/comments", comment);

        return new ResponseEntity<Comment>(HttpStatus.OK);
    }
}
