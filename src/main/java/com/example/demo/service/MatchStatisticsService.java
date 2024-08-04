package com.example.demo.service;

import com.example.demo.entity.MatchStatistics;

import java.util.List;

public interface MatchStatisticsService {

    void save(MatchStatistics theMatchStatistics);

    void save(int matchId, MatchStatistics theMatchStatistics);

    List<MatchStatistics> filterMatchStatistics(String column, String condition, int value);
}
