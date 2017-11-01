package com.dao;

import com.models.GlobalStatistic;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("GlobalStatisticsDao")
@Transactional
public class GlobalStatisticsDaoImpl extends AbstractDao<Integer, GlobalStatistic> implements GlobalStatisticsDao
{
    @SuppressWarnings("unchecked")
    public List<GlobalStatistic> getAll()
    {
        return (List<GlobalStatistic>) this.createEntityCriteria().list();
    }
}
