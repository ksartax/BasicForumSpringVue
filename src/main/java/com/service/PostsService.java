package com.service;

import com.dvo.CommentView;
import com.dvo.PostView;
import com.models.Post;

import java.util.List;

public interface PostsService {
    public List<PostView> getAll(int limit);

    public List<Post> getAllByUserId(int id);

    public List<Post> getAllByCategoryId(int id);

    public Post get(int id);

    public Post get(String id);

    public Post add(Post post, int categoryId);

    public void remove(int id);

    public List<CommentView> getCommentByPostId(int id);
}
