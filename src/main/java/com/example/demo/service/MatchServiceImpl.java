package com.example.demo.service;

import com.example.demo.dao.MatchDAO;
import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Match2;
import com.example.demo.entity.Result;
import com.example.demo.rest.dto.MatchRequest;
import com.example.demo.rest.dto.MatchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDAO matchDAO;

    public MatchServiceImpl(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }


    @Override
    public MatchResponse save(MatchRequest match) {
        // calculate Result
        Result finalresult;
        if (match.getHomeTeamScore() > match.getAwayTeamScore()){
            finalresult = Result.WIN;

        } else if (match.getHomeTeamScore() < match.getAwayTeamScore()) {
            finalresult = Result.LOSS;
        } else {
            finalresult = Result.DRAW;
        }

        // MAPPER
        Match mapperMatch = mapRequest(match);
        mapperMatch.setResult(finalresult);
        Match dbMatch = matchDAO.save(mapperMatch);
        MatchResponse matchResponse = mapMatchRequest(dbMatch);

        return matchResponse;
    }

    @Override
    public Match2 save2(Match2 match) {
        return matchDAO.save2(match);
    }

    private Match mapRequest(MatchRequest match) {
        Match dbmatch = new Match();
        dbmatch.setAwayTeamScore(match.getAwayTeamScore());
        dbmatch.setHomeTeamScore(match.getHomeTeamScore());
        dbmatch.setAwayTeam(match.getAwayTeam());
        dbmatch.setHomeTeam(match.getHomeTeam());
        return dbmatch;
    }

    private MatchResponse mapMatchRequest(Match match){
        MatchResponse matchResponse = new MatchResponse();
        matchResponse.setAwayTeam(match.getAwayTeam());
        matchResponse.setHomeTeam(match.getHomeTeam());
        matchResponse.setAwayTeamScore(match.getAwayTeamScore());
        matchResponse.setHomeTeamScore(match.getHomeTeamScore());
        return matchResponse;
    }

    @Override
    public List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague) {
        return matchDAO.findByHomeTeamLeagueOrAwayTeamLeague(homeLeague, awayLeague);

    }

    @Override
    public List<Match> findByLeague(League league) {

        return matchDAO.findByLeague(league);
    }

    @Override
    public Match findLatestMatchByResult(List<Match> matches, Result result) {
       return matchDAO.findLatestMatchByResult(matches, result);

    }

    @Override
    public List<Match> findByTeam(FootballTeam team) {
        return matchDAO.findByTeam(team);
    }
    @Override
    public List<Match2> findByTeam2(FootballTeam team) {
        return matchDAO.findByTeam2(team);
    }
}
