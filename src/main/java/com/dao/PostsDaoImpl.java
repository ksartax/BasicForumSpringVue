package com.dao;

import com.models.Post;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("PostsDao")
@Transactional
public class PostsDaoImpl extends AbstractDao<Integer, Post> implements PostsDao {
    @SuppressWarnings("unchecked")
    public List<Post> getAll(int limit) {
        return (List<Post>) this.createEntityCriteria()
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setMaxResults(limit)
                .addOrder(Order.desc("id"))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getAllByUserId(int id) {
        return (List<Post>) createEntityCriteria()
                .add(
                        Restrictions.eq("user.id", id)
                )
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getAllByCategoryId(int id) {
        return (List<Post>) createEntityCriteria()
                .add(
                        Restrictions.eq("category.id", id)
                )
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public Post get(int id) {
        return getByKey(id);
    }

    public Post add(Post post) {
        persist(post);

        return post;
    }

    public void remove(Post post) {
        delete(post);
    }
}
