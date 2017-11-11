package com.service;

import com.models.Category;
import java.util.List;

public interface CategoriesService
{
    public List<Category> getAll();
    public Category get(int id);
    public Category incrementPost(Category category, int count);
    public Category add(Category category);
    public void remove(int id);
}
