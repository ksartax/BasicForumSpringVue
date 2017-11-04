package com.dao;

import com.models.Category;
import com.models.Comment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("CategoriesDao")
@Transactional
public class CategoriesDaoImpl extends AbstractDao<Integer, Category> implements CategoriesDao
{
    @SuppressWarnings("unchecked")
    public List<Category> getAll()
    {
        return (List<Category>) this.createEntityCriteria().list();
    }

    public Category get(int id) {
        return this.getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> getByLevel(int level) {
        return this.createEntityCriteria().add(Restrictions.eq("level", level)).list();
    }

    public Category add(Category category) {
        persist(category);

        return category;
    }
}
