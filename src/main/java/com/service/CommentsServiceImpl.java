package com.service;

import com.configurations.Auth;
import com.dao.CommentsDao;
import com.dao.PostsDao;
import com.dao.UsersDao;
import com.dvo.CommentView;
import com.models.Comment;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("commentsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private CommentsDao commentsDao;
    private PostsDao postsDao;
    private UsersDao usersDao;
    private DozerBeanMapper beanMapper;

    @Autowired
    public CommentsServiceImpl(
            CommentsDao commentsDao,
            PostsDao postsDao,
            UsersDao usersDao,
            DozerBeanMapper beanMapper
    ) {
        this.commentsDao = commentsDao;
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.beanMapper = beanMapper;
    }

    public List<CommentView> getAll(int limit) {
        return commentsDao.getAll(limit).stream()
                .map(entity -> beanMapper.map(
                        entity, CommentView.class
                ))
                .collect(Collectors.toList());
    }

    public List<Comment> getAllByUserId(int id) {
        return commentsDao.getAllByUserId(id);
    }

    public List<Comment> getAllByPostId(int postId) {
        return commentsDao.getAllByPostId(postId);
    }

    public Comment add(Comment comment, int postId) {
        comment.setUser(usersDao.findByUserName((new Auth().getLoginUser()).getUsername()));
        comment.getUser().getStatistics().incrementCommentCount(1);

        comment.setPost(postsDao.get(postId));
        comment.getPost().incrementCommentCount(1);

        return this.commentsDao.add(comment);
    }

    public void remove(int id) {
        Comment comment = this.commentsDao.get(id);

        comment.getUser().getStatistics().decrementCommentCount(1);
        comment.getPost().decrementCommentCount(1);

        this.commentsDao.remove(comment);
    }
}
