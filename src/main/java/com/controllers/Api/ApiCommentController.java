package com.controllers.Api;

import com.models.Comment;
import com.service.CommentsService;
import com.service.GlobalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/comment")
@RestController
public class ApiCommentController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Comment> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit) {
        return this.commentsService.getAll(limit);
    }

    @RequestMapping(path = "/add/post/{id}", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Comment comment, @PathVariable("id") int id) {
        commentsService.add(comment, id);
        globalStatisticsService.increment(globalStatisticsService.getByTitle("Komentarze"), 1);

        template.convertAndSend("/post/" + id + "/comments", "");
        template.convertAndSend("/comment", "");
        template.convertAndSend("/globalStatistic", "");

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/remove/{commentId}/post/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId) {
        this.commentsService.remove(commentId);
        globalStatisticsService.decrement(globalStatisticsService.getByTitle("Komentarze"), 1);

        template.convertAndSend("/globalStatistic", "");
        template.convertAndSend("/post/" + postId + "/comments", "");

        return new ResponseEntity(HttpStatus.OK);
    }
}
