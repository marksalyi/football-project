package com.example.demo.dao;

import com.example.demo.entity.MatchStatistics;

import java.util.List;

public interface MatchStatisticsDAO {

    void save(MatchStatistics theMatchStatistics);

    List<MatchStatistics> filterMatchStatistics(String column, String condition, int value);
}
