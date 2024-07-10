package com.example.demo.service;

import com.example.demo.dao.MatchDAO;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;
import com.example.demo.rest.dto.MatchRequest;
import com.example.demo.rest.dto.MatchResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDAO matchDAO;

    @Autowired
    private ModelMapper mapper;
    public MatchServiceImpl(MatchDAO matchDAO, ModelMapper mapper) {
        this.matchDAO = matchDAO;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public MatchResponse save(MatchRequest match) {
        // calculate Result
        Result finalresult;
        if (match.getHomeTeamScore() > match.getAwayTeamScore()){
            finalresult = Result.HOME;

        } else if (match.getHomeTeamScore() < match.getAwayTeamScore()) {
            finalresult = Result.LOSS;
        } else {
            finalresult = Result.DRAW;
        }

        // MAPPER
        Match mapperMatch = mapper.map(match, Match.class);
        mapperMatch.setResult(finalresult);
        Match dbMatch = matchDAO.save(mapperMatch);

        return mapper.map(dbMatch, MatchResponse.class);
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
}
