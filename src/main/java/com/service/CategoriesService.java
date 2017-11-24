package com.service;

import com.dvo.CategoryView;
import com.dvo.PostView;
import com.models.Category;

import java.util.List;

public interface CategoriesService {
    public List<CategoryView> getAll(int limit);

    public Category get(int id);

    public Category incrementPost(Category category, int count);

    public Category add(Category category);

    public void remove(int id);

    public List<PostView> getPostsByCategoryId(int id);
}
