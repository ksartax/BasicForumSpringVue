package com.dao;

import com.models.Post;

import java.util.List;

public interface PostsDao {
    public List<Post> getAll(int limit);

    public List<Post> getAllByUserId(int id);

    public List<Post> getAllByCategoryId(int id);

    public Post get(int id);

    public Post get(String id);

    public Post add(Post post);

    public void remove(Post post);
}
