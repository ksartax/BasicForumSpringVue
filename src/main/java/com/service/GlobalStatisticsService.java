package com.service;

import com.models.GlobalStatistic;

import java.util.List;

public interface GlobalStatisticsService {
    public List<GlobalStatistic> getAll();

    public GlobalStatistic increment(GlobalStatistic globalStatistic, int count);

    public GlobalStatistic decrement(GlobalStatistic globalStatistic, int count);

    public GlobalStatistic getByTitle(String title);
}
