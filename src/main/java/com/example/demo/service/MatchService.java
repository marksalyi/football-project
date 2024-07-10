package com.example.demo.service;

import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;
import com.example.demo.rest.dto.MatchRequest;
import com.example.demo.rest.dto.MatchResponse;

import java.util.List;

public interface MatchService {
    MatchResponse save(MatchRequest match);
    List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague);

    List<Match> findByLeague(League league);

    Match findLatestMatchByResult(List<Match> matches, Result result);
}
