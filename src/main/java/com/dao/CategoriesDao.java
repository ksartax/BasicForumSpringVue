package com.dao;

import com.models.Category;

import java.util.List;

public interface CategoriesDao {
    public List<Category> getAll(int limit);

    public Category get(int id);

    public Category add(Category category);

    public void remove(Category category);
}
