package com.dao;

import com.models.Post;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("PostsDao")
@Transactional
public class PostsDaoImpl extends AbstractDao<Integer, Post> implements PostsDao
{
    @SuppressWarnings("unchecked")
    public List<Post> getAll()
    {
        return (List<Post>) this.createEntityCriteria().list();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getAllByUserId(int id) {
        return (List<Post>) createEntityCriteria()
                .add(Restrictions.eq("user.id", id)).list();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getAllByCategoryId(int id) {
        return (List<Post>) createEntityCriteria()
                .add(Restrictions.eq("category.id", id)).list();
    }

    public Post get(int id) {
        return getByKey(id);
    }

    public Post add(Post post) {
        persist(post);

        return post;
    }
}
