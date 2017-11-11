package com.dao;

import com.models.Category;
import java.util.List;

public interface CategoriesDao
{
    public List<Category> getAll();
    public Category get(int id);
    public Category add(Category category);
    public void remove(int id);
}
