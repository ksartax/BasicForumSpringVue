package com.service;

import com.dao.UsersDao;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("usersService")
@ComponentScan(value = "spring.dao")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    public List<User> getAll(int limit) {
        return usersDao.getAll(limit);
    }

    public User get(int id) {
        return usersDao.get(id);
    }
}
