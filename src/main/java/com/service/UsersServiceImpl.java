package com.service;

import com.component.image.FileComponent;
import com.configurations.Auth;
import com.dao.StatisticsDao;
import com.dao.UsersDao;
import com.models.Statistic;
import com.models.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service("usersService")
@ComponentScan(value = {"spring.dao", "spring.component.image"})
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private UsersDao usersDao;
    private StatisticsDao statisticsDao;
    private FileComponent fileComponent;

    public UsersServiceImpl(
            UsersDao usersDao,
            StatisticsDao statisticsDao,
            FileComponent fileComponent
    ) {
        this.usersDao = usersDao;
        this.statisticsDao = statisticsDao;
        this.fileComponent = fileComponent;
    }

    public List<User> getAll(int limit) {
        return usersDao.getAll(limit);
    }

    public User get(int id) {
        return usersDao.get(id);
    }

    public User getByUsername(String username) {
        return usersDao.findByUserName(username);
    }

    public User add(User user) {
        user.setRole("ROLE_USER");
        user.setPathImg("a.jpg");
        user.setStatistics(statisticsDao.add(new Statistic()));

        return usersDao.add(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUserName(username);

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }

    public void importImage(@RequestParam("file") MultipartFile file) {
        User user = usersDao.findByUserName((new Auth().getLoginUser()).getUsername());

        fileComponent.importImage(file, user.getId());
        user.setPathImg(fileComponent.getFileName());
        usersDao.add(user);
    }
}
