package com.dao;

import com.models.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl extends AbstractDao<Integer, User> implements UsersDao {
    @SuppressWarnings("unchecked")
    public List<User> getAll(int limit) {
        return (List<User>) createEntityCriteria()
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public User get(int id) {
        return this.getByKey(id);
    }

    public User findByUserName(String username) {
        return (User) createEntityCriteria()
                .add(
                        Restrictions.eq("username", username)
                )
                .uniqueResult();
    }

    public User add(User user) {
        this.persist(user);

        return user;
    }
}
