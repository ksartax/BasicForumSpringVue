package com.service;

import com.dao.PostsDao;
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

    public List<Post> getAll() {
        return this.postsDao.getAll();
    }

    public List<Post> getAllByUserId(int id) {
        return postsDao.getAllByUserId(id);
    }

    public List<Post> getAllByCategoryId(int id) {
        return postsDao.getAllByCategoryId(id);
    }

    public Post get(int id) {
        return postsDao.get(id);
    }
}
