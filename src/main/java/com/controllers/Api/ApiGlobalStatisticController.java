package com.controllers.Api;

import com.models.GlobalStatistic;
import com.service.GlobalStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api/global-statistic")
public class ApiGlobalStatisticController
{
    @Autowired
    private GlobalStatisticsService globalStatisticsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<GlobalStatistic> index()
    {
        return this.globalStatisticsService.getAll();
    }
}
