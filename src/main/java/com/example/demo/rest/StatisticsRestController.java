package com.example.demo.rest;

import com.example.demo.dao.StatisticsDAO;
import com.example.demo.entity.Statistics;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsRestController {
    private StatisticsDAO statisticsDAO;

    public StatisticsRestController(StatisticsDAO theStatisticsDAO) {
        statisticsDAO = theStatisticsDAO;
    }

    @PostMapping("/newstatistics")
    public int createMatch(@RequestBody Statistics theStatistics){

        int dbStatisics = statisticsDAO.save(theStatistics);
        return dbStatisics;
    }
}
