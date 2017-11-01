package com.dao;

import com.models.Comment;
import java.util.List;

public interface CommentsDao
{
    public List<Comment> getAll();
}
