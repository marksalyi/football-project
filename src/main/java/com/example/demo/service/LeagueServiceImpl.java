package com.example.demo.service;

import com.example.demo.dao.LeagueDAO;
import com.example.demo.entity.League;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueDAO leagueDAO;

    public LeagueServiceImpl(LeagueDAO leagueDAO) {
        this.leagueDAO = leagueDAO;
    }

    @Override
    public List<League> findAll() {
        return leagueDAO.findAll();
    }

    @Override
    public League findById(int theId) {
        return leagueDAO.findById(theId);
    }

    @Transactional
    @Override
    public League save(League theLeague) {
        return leagueDAO.save(theLeague);
    }

    @Transactional
    @Override
    public void deleteById(int theId){
        leagueDAO.deleteById(theId);
    }


    @Transactional
    @Override
    public void update(League theLeague) {
        leagueDAO.update(theLeague);
    }
}
