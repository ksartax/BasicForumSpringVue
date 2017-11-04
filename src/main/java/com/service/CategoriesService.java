package com.service;

import com.models.Category;
import java.util.List;

public interface CategoriesService
{
    public List<Category> getAll();
    public Category get(int id);
    public List<Category> getByLevel(int level);
    public Category incrementPost(Category category, int count);
}
