package com.service;

import com.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsersService {
    public List<User> getAll(int limit);
    public User get(int id);
}
