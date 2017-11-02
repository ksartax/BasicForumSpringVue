package com.controllers.Api;

import com.models.Comment;
import com.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Comment> index()
    {
        return this.commentsService.getAll();
    }
}
