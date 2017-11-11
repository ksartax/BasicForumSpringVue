package com.service;

import com.models.User;

import java.util.List;

public interface UsersService {
    public List<User> getAll(int limit);

    public User get(int id);

    public User getByUsername(String username);

    public User add(User user);
}
