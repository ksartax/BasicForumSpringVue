package com.service;

import com.dao.GlobalStatisticsDao;
import com.models.GlobalStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("globalStatisticsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class GlobalStatisticsServiceImpl implements GlobalStatisticsService
{
    @Autowired
    private GlobalStatisticsDao globalStatisticsDao;

    public List<GlobalStatistic> getAll() {
        return globalStatisticsDao.getAll();
    }
}
