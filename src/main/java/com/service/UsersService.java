package com.service;

import com.models.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    public List<User> getAll(int limit);

    public User get(int id);

    public User getByUsername(String username);

    public User add(User user);

    public void importImage(@RequestParam("file") MultipartFile file);

    public boolean checkEmail(String email);

    public boolean checkUsername(String username);
}
