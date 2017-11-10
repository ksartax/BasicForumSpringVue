package com.service;

import com.dao.FriendsDao;
import com.models.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("friendsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class FriendsServiceImpl implements FriendsService
{
    @Autowired
    private FriendsDao friendsDao;

    public List<Friend> getAllByUser(int id)
    {
        return friendsDao.getAllByUser(id);
    }
}
