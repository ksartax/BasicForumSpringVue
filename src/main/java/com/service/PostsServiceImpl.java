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
}
