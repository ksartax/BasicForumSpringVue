package com.service;

import com.configurations.Auth;
import com.dao.CategoriesDao;
import com.dao.GlobalStatisticsDao;
import com.dao.PostsDao;
import com.dao.UsersDao;
import com.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("postsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class PostsServiceImpl implements PostsService
{
    @Autowired
    private PostsDao postsDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private CategoriesDao categoriesDao;

    public List<Post> getAll(int limit)
    {
        return this.postsDao.getAll(limit);
    }

    public List<Post> getAllByUserId(int id)
    {
        return postsDao.getAllByUserId(id);
    }

    public List<Post> getAllByCategoryId(int id)
    {
        return postsDao.getAllByCategoryId(id);
    }

    public Post get(int id)
    {
        return postsDao.get(id);
    }

    public Post add(Post post, int categoryId)
    {
        post.setUser(usersDao.findByUserName((new Auth().getLoginUser()).getUsername()));
        post.getUser().getStatistics().incrementPostCount(1);

        post.setCategory(categoriesDao.get(categoryId));
        post.getCategory().incrementPost(1);

        return postsDao.add(post);
    }

    public void remove(int id)
    {
        postsDao.remove(id);
    }
}
