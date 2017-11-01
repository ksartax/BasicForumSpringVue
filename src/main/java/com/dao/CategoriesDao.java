package com.dao;

import com.models.Category;
import java.util.List;

public interface CategoriesDao
{
    public List<Category> getAll();
    public Category get(int id);
    public List<Category> getByLevel(int level);
}
