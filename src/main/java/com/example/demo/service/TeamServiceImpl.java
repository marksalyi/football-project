package com.example.demo.service;

import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.FootballTeam;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamDAO teamDAO;

    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public FootballTeam findById(int theId) {
        return teamDAO.findById(theId);
    }

    @Override
    public FootballTeam save(FootballTeam footballTeam) {
        return teamDAO.save(footballTeam);
    }

    @Override
    public void update(FootballTeam footballTeam) {
        teamDAO.save(footballTeam);
    }

    @Override
    public void delete(int theId) {
        teamDAO.delete(theId);
    }
}
