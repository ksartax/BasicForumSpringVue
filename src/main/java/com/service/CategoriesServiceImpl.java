package com.service;

import com.configurations.Auth;
import com.dao.CategoriesDao;
import com.dao.UsersDao;
import com.dvo.CategoryView;
import com.dvo.PostView;
import com.models.Category;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("categoriesService")
@ComponentScan(value = "spring.dao")
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesDao categoriesDao;
    private UsersDao usersDao;
    private DozerBeanMapper beanMapper;

    @Autowired
    public CategoriesServiceImpl(
            CategoriesDao categoriesDao,
            UsersDao usersDao,
            DozerBeanMapper beanMapper
    ) {
        this.categoriesDao = categoriesDao;
        this.usersDao = usersDao;
        this.beanMapper = beanMapper;
    }

    public List<CategoryView> getAll(int limit) {
        return categoriesDao.getAll(limit).stream()
                .map(entity -> beanMapper.map(
                        entity, CategoryView.class
                ))
                .collect(Collectors.toList());
    }

    public Category get(String id) {
        return categoriesDao.get(id);
    }

    public List<PostView> getPostsByCategoryId(int id) {
        return categoriesDao
                .get(id)
                .getPosts()
                .stream()
                .map(entity -> beanMapper.map(
                        entity, PostView.class
                ))
                .collect(Collectors.toList());
    }

    public Category incrementPost(Category category, int count) {
        category.incrementPost(count);

        return categoriesDao.add(category);
    }

    public Category add(Category category) {
        category.setUser(usersDao.findByUserName((new Auth().getLoginUser()).getUsername()));

        return categoriesDao.add(category);
    }

    public void remove(int id) {
        Category category = categoriesDao.get(id);

        this.categoriesDao.remove(category);
    }
}
