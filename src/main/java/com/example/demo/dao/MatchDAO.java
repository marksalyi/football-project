package com.example.demo.dao;

import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Match2;
import com.example.demo.entity.Result;

import java.util.List;

public interface MatchDAO {
    Match save(Match match);
    Match2 save2(Match2 match);
    List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague);

    List<Match> findByLeague(League league);

    Match findLatestMatchByResult(List<Match> matches, Result result);

    List<Match> findByTeam(FootballTeam team);

    List<Match2> findByTeam2(FootballTeam team);

}
