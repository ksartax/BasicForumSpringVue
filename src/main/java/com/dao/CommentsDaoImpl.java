package com.dao;

import com.models.Comment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("CommentsDao")
@Transactional
public class CommentsDaoImpl extends AbstractDao<Integer, Comment> implements CommentsDao
{
    @SuppressWarnings("unchecked")
    public List<Comment> getAll()
    {
        return (List<Comment>) this.createEntityCriteria().list();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllByUserId(int id) {
        return (List<Comment>) createEntityCriteria()
                .add(Restrictions.eq("user.id", id)).list();
    }
}
