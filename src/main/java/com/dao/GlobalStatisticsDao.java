package com.dao;

import com.models.GlobalStatistic;

import java.util.List;

public interface GlobalStatisticsDao {
    public List<GlobalStatistic> getAll();

    public GlobalStatistic add(GlobalStatistic globalStatistic);

    public GlobalStatistic getByTitle(String title);
}
