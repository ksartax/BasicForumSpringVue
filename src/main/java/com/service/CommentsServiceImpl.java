package com.service;

import com.dao.CommentsDao;
import com.dao.GroupsDao;
import com.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("commentsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class CommentsServiceImpl implements CommentsService
{
    @Autowired
    private CommentsDao commentsDao;

    public List<Comment> getAll() {
        return commentsDao.getAll();
    }
}
