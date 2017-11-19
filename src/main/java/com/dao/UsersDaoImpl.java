package com.dao;

import com.models.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
                .addOrder(Order.desc("id"))
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

    public boolean checkEmail(String email) {
        List<User> users = (List<User>) createEntityCriteria()
                .add(
                        Restrictions.eq("email", email)
                )
                .list();

        return users.size() == 0 ? true : false;
    }

    public boolean checkUsername(String username) {
        List<User> users = (List<User>) createEntityCriteria()
                .add(
                        Restrictions.eq("username", username)
                )
                .list();

        return users.size() == 0 ? true : false;
    }

    public User add(User user) {
        this.persist(user);

        return user;
    }
}
