package com.service;

import com.configurations.Auth;
import com.dao.CategoriesDao;
import com.dao.PostsDao;
import com.dao.UsersDao;
import com.dvo.CommentView;
import com.dvo.PostView;
import com.models.Post;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("postsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class PostsServiceImpl implements PostsService {

    private PostsDao postsDao;
    private UsersDao usersDao;
    private CategoriesDao categoriesDao;
    private DozerBeanMapper beanMapper;

    @Autowired
    public PostsServiceImpl(
            PostsDao postsDao,
            UsersDao usersDao,
            CategoriesDao categoriesDao,
            DozerBeanMapper beanMapper
    ) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.categoriesDao = categoriesDao;
        this.beanMapper = beanMapper;
    }

    public List<PostView> getAll(int limit) {
        return this.postsDao.getAll(limit).stream()
                .map(entity -> beanMapper.map(
                        entity, PostView.class
                ))
                .collect(Collectors.toList());
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

    public Post get(String id) {
        return postsDao.get(id);
    }

    public Post add(Post post, int categoryId) {
        post.setUser(usersDao.findByUserName((new Auth().getLoginUser()).getUsername()));
        post.getUser().getStatistics().incrementPostCount(1);

        post.setCategory(categoriesDao.get(categoryId));
        post.getCategory().incrementPost(1);

        return postsDao.add(post);
    }

    public void remove(int id) {
        Post post = postsDao.get(id);

        post.getUser().getStatistics().decrementPostCount(1);
        post.getCategory().decrementPost(1);

        try {
            postsDao.remove(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CommentView> getCommentByPostId(int id) {
        return postsDao
                .get(id)
                .getComments()
                .stream()
                .map(entity -> beanMapper.map(
                        entity, CommentView.class
                ))
                .collect(Collectors.toList());
    }
}
