package com.dao;

import com.models.Post;
import java.util.List;

public interface PostsDao
{
    public List<Post> getAll();
    public List<Post> getAllByUserId(int id);
    public List<Post> getAllByCategoryId(int id);
    public Post get(int id);
    public Post add(Post post);
}
