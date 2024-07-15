package com.example.demo.dao;

import com.example.demo.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsImpl implements StatisticsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StatisticsImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int save(Statistics theStatistics) {
        return jdbcTemplate.update("INSERT INTO statistics (name, result) VALUES(?,?)",
                new Object[] { theStatistics.getName(), theStatistics.getResult() });
    }
}
