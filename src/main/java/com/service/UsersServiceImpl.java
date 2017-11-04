package com.service;

import com.dao.StatisticsDao;
import com.dao.UsersDao;
import com.models.Statistic;
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
    @Autowired
    StatisticsDao statisticsDao;

    public List<User> getAll(int limit) {
        return usersDao.getAll(limit);
    }

    public User get(int id) {
        return usersDao.get(id);
    }

    public User getByUsername(String username) {
        return (User) this.loadUserByUsername(username);
    }

    public User add(User user) {
        user.setRole("ROLE_USER");
        user.setPathImg("a.jpg");
        user.setStatistics(statisticsDao.add(new Statistic()));

        return  usersDao.add(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = usersDao.findByUserName(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }
}
