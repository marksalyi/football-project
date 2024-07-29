package com.example.demo.service;

import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Match2;
import com.example.demo.entity.Result;
import com.example.demo.rest.dto.MatchRequest;
import com.example.demo.rest.dto.MatchResponse;

import java.util.List;

public interface MatchService {
    MatchResponse save(MatchRequest match);
    Match2 save2(Match2 match);
    List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague);

    List<Match> findByLeague(League league);

    Match findLatestMatchByResult(List<Match> matches, Result result);

    List<Match> findByTeam(FootballTeam team);

    List<Match2> findByTeam2(FootballTeam team);
}
