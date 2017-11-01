package com.dao;

import com.models.Group;
import com.models.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl extends AbstractDao<Integer, User> implements UsersDao
{
    @SuppressWarnings("unchecked")
    public List<User> getAll()
    {
        return (List<User>) createEntityCriteria().list();
    }

    public User get(int id) {
        return this.getByKey(id);
    }
}
