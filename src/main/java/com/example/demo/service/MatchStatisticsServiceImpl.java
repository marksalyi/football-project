package com.example.demo.service;

import com.example.demo.dao.MatchDAO;
import com.example.demo.dao.MatchStatisticsDAO;
import com.example.demo.entity.Match;
import com.example.demo.entity.MatchStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchStatisticsServiceImpl implements MatchStatisticsService {

    @Autowired
    private MatchStatisticsDAO matchStatisticsDAO;

    @Autowired
    private MatchDAO matchDAO;

    public MatchStatisticsServiceImpl(MatchStatisticsDAO theMatchStatisticsDAO, MatchDAO theMatchDAO) {
        this.matchStatisticsDAO = theMatchStatisticsDAO;
        this.matchDAO = theMatchDAO;
    }


    @Override
    @Transactional
    public void save(MatchStatistics theMatchStatistics) {

        matchStatisticsDAO.save(theMatchStatistics);
    }

    @Override
    @Transactional
    public void save(int matchId, MatchStatistics theMatchStatistics) {
        Match dbMatch = matchDAO.findById(matchId);

        theMatchStatistics.setMatchId(dbMatch);
        matchStatisticsDAO.save(theMatchStatistics);

    }

    @Override
    public List<MatchStatistics> filterMatchStatistics(String column, String condition, int value) {
        return matchStatisticsDAO.filterMatchStatistics(column, condition, value);
    }
}
