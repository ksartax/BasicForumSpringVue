package com.service;

import com.models.Comment;
import java.util.List;

public interface CommentsService
{
    public List<Comment> getAll();
    public List<Comment> getAllByUserId(int id);
}
