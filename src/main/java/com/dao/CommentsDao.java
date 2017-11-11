package com.dao;

import com.models.Comment;
import java.util.List;

public interface CommentsDao
{
    public List<Comment> getAll(int limit);
    public List<Comment> getAllByUserId(int id);
    public List<Comment> getAllByPostId(int postId);
    public Comment add(Comment comment);
    public void remove(int id);
}
