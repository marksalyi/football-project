package com.example.demo.service;

import com.example.demo.dao.StatisticsDAO;
import com.example.demo.entity.Statistics;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsDAO statisticsDAO;

    public StatisticsServiceImpl(StatisticsDAO statisticsDAO) {
        this.statisticsDAO = statisticsDAO;
    }

    @Transactional
    @Override
    public int save(Statistics statistics) {
       return statisticsDAO.save(statistics);
    }
}
