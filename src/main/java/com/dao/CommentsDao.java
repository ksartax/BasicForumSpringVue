package com.dao;

import com.models.Comment;
import java.util.List;

public interface CommentsDao
{
    public List<Comment> getAll();
    public List<Comment> getAllByUserId(int id);
    public List<Comment> getAllByPostId(int postId);
}
