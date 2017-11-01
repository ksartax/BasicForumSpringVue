package com.service;

import com.models.User;
import java.util.List;

public interface UsersService {
    public List<User> getAll();
    public User get(int id);
}
