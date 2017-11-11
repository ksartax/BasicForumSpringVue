package com.service;

import com.models.Post;
import java.util.List;

public interface PostsService
{
    public List<Post> getAll(int limit);
    public List<Post> getAllByUserId(int id);
    public List<Post> getAllByCategoryId(int id);
    public Post get(int id);
    public Post add(Post post, int categoryId);
    public void remove(int id);
}
