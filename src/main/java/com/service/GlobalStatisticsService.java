package com.service;

import com.models.GlobalStatistic;

import java.util.List;

public interface GlobalStatisticsService {
    public List<GlobalStatistic> getAll();

    public GlobalStatistic getByTitle(String title);

    public void decrementCategory(int categoryId, int count);

    void incrementCategory(int count);

    public void decrementPosts(int postId, int count);

    public void incrementPosts(int count);

    public void decrementComments(int count);

    public void incrementComments(int count);
}
