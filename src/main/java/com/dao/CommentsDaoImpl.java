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
    public List<Comment> getAll(int limit)
    {
        return (List<Comment>) this.createEntityCriteria()
                .setMaxResults(limit)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllByUserId(int id)
    {
        return (List<Comment>) createEntityCriteria()
                .add(
                        Restrictions.eq("user.id", id)
                )
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllByPostId(int postId)
    {
        return (List<Comment>) createEntityCriteria()
                .add(
                        Restrictions.eq("post.id", postId)
                )
                .list();
    }

    public Comment add(Comment comment)
    {
        this.persist(comment);

        return comment;
    }

    public void remove(int id)
    {
        delete(getByKey(id));
    }
}
