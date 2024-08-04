package com.example.demo.service;

import com.example.demo.entity.League;
import com.example.demo.entity.Match;

import java.util.List;

public interface LeagueService {

    List<League> findAll();

    League findById(int theId);

    League save(League theLeague);

    void deleteById(int theId);

    void update(League theLeague);

    List<Match> findAllMatchesByLeagueId(int theId);

    void getResults(int theId);

    void getResults2(int theId);

    void assignedTeamToLeague(int leagueId, int teamId);
}
