package com.dao;

import com.models.User;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl extends AbstractDao<Integer, User> implements UsersDao {
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return (List<User>) createEntityCriteria().list();
    }
}