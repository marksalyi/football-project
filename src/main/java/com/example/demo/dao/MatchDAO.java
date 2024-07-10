package com.example.demo.dao;

import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;

import java.util.List;

public interface MatchDAO {
    Match save(Match match);
    List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague);

    List<Match> findByLeague(League league);

    Match findLatestMatchByResult(List<Match> matches, Result result);
}
