package com.service;

import com.dao.CategoriesDao;
import com.dao.CommentsDao;
import com.models.Category;
import com.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("categoriesService")
@ComponentScan(value = "spring.dao")
@Transactional
public class CategoriesServiceImpl implements CategoriesService
{
    @Autowired
    private CategoriesDao categoriesDao;

    public List<Category> getAll()
    {
        return categoriesDao.getAll();
    }

    public Category get(int id) {
        return categoriesDao.get(id);
    }

    public List<Category> getByLevel(int level) {
        return categoriesDao.getByLevel(level);
    }

    public Category incrementPost(Category category, int count) {
        category.incrementPost(count);

        return categoriesDao.add(category);
    }
}
