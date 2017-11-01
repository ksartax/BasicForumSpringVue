package com.dao;

import com.models.Post;
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
}
