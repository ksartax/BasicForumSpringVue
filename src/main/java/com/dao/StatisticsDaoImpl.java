package com.dao;

import com.models.Statistic;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository("StatisticsDao")
@Transactional
public class StatisticsDaoImpl extends AbstractDao<Integer, Statistic> implements StatisticsDao
{
    public Statistic add(Statistic statistic) {
        int key = (Integer) getSession().save(statistic);

        return this.getByKey(key);
    }
}
