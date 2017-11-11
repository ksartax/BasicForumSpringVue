package com.service;

import com.models.Comment;

import java.util.List;

public interface CommentsService {
    public List<Comment> getAll(int limit);

    public List<Comment> getAllByUserId(int id);

    public List<Comment> getAllByPostId(int postId);

    public Comment add(Comment comment, int postId);

    public void remove(int id);
}
