package com.service;

import com.configurations.Auth;
import com.dao.CategoriesDao;
import com.dao.GlobalStatisticsDao;
import com.dao.UsersDao;
import com.models.Category;
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
    @Autowired
    private UsersDao usersDao;

    public List<Category> getAll()
    {
        return categoriesDao.getAll();
    }

    public Category get(int id)
    {
        return categoriesDao.get(id);
    }

    public Category incrementPost(Category category, int count)
    {
        category.incrementPost(count);

        return categoriesDao.add(category);
    }

    public Category add(Category category)
    {
        category.setUser(usersDao.findByUserName((new Auth().getLoginUser()).getUsername()));

        return categoriesDao.add(category);
    }

    public void remove(int id)
    {
        this.categoriesDao.remove(id);
    }
}
