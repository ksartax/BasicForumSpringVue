package com.dao;

import com.models.User;
import java.util.List;

public interface UsersDao
{
    public List<User> getAll(int limit);
    public User get(int id);
    public User findByUserName(String username);
    public User add(User user);
}
