package com.dao;

import com.models.Category;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("CategoriesDao")
@Transactional
public class CategoriesDaoImpl extends AbstractDao<Integer, Category> implements CategoriesDao {
    @SuppressWarnings("unchecked")
    public List<Category> getAll() {
        return (List<Category>) this.createEntityCriteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public Category get(int id) {
        return this.getByKey(id);
    }

    public Category add(Category category) {
        persist(category);

        return category;
    }

    public void remove(Category category) {
        delete(category);
    }
}
