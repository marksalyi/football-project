package com.example.demo.dao;

import com.example.demo.entity.League;
import com.example.demo.entity.Match;

import java.util.HashSet;
import java.util.List;

public interface LeagueDAO {

    List<League> findAll();

    League findById(int theId);

    League save(League theLeague);

    void deleteById(int theId);

    void update(League theLeague);

    List<Match> findAllMatchesByLeagueId(int theId);


}
