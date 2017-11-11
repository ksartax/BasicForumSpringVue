package com.dao;

import com.models.GlobalStatistic;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("GlobalStatisticsDao")
@Transactional
public class GlobalStatisticsDaoImpl extends AbstractDao<Integer, GlobalStatistic> implements GlobalStatisticsDao {
    @SuppressWarnings("unchecked")
    public List<GlobalStatistic> getAll() {
        return (List<GlobalStatistic>) this.createEntityCriteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public GlobalStatistic add(GlobalStatistic globalStatistic) {
        persist(globalStatistic);

        return globalStatistic;
    }

    public GlobalStatistic getByTitle(String title) {
        return (GlobalStatistic) this.createEntityCriteria()
                .add(
                        Restrictions.eq("title", title)
                )
                .uniqueResult();
    }
}
