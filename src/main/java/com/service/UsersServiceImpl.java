package com.service;

import com.dao.UsersDao;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service("usersService")
@ComponentScan(value = "spring.dao")
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    public List<User> getAll(int limit) {
        return usersDao.getAll(limit);
    }

    public User get(int id) {
        return usersDao.get(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.err.println(username);
        User user = usersDao.findByUserName(username);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }
}
