package com.dao;

import com.models.Comment;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("CommentsDao")
@Transactional
public class CommentsDaoImpl extends AbstractDao<Integer, Comment> implements CommentsDao {
    @SuppressWarnings("unchecked")
    public List<Comment> getAll(int limit) {
        return (List<Comment>) this.createEntityCriteria()
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .setMaxResults(limit)
                .list();
    }

    public Comment get(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllByUserId(int id) {
        return (List<Comment>) createEntityCriteria()
                .add(
                        Restrictions.eq("user.id", id)
                )
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllByPostId(int postId) {
        return (List<Comment>) createEntityCriteria()
                .add(
                        Restrictions.eq("post.id", postId)
                )
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public Comment add(Comment comment) {
        this.persist(comment);

        return comment;
    }

    public void remove(Comment comment) {
        delete(comment);
    }
}
