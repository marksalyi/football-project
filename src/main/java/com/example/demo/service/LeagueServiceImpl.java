package com.example.demo.service;

import com.example.demo.dao.LeagueDAO;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;
import com.example.demo.entity.TeamResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LeagueServiceImpl
        implements LeagueService {

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

    @Override
    public League save(League theLeague) {
        return leagueDAO.save(theLeague);
    }

    @Override
    public void deleteById(int theId) {
        leagueDAO.deleteById(theId);
    }


    @Override
    public void update(League theLeague) {
        leagueDAO.save(theLeague);
    }

    @Override
    public List<Match> findAllMatchesByLeagueId(int theId) {
        return leagueDAO.findAllMatchesByLeagueId(theId);
    }

    public void getResults(int theId) {
        List<Match> allMatches = leagueDAO.findAllMatchesByLeagueId(theId);

        Map<String, Map<String, Integer>> finalResult = new HashMap<>();
        String winnerKey = "WIN";
        String loserKey = "LOSS";
        String drawerKey = "DRAW";

        for (Match match : allMatches) {
            if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
                finalResult.putIfAbsent(match.getHomeTeam().getTeamName(), new HashMap<>()); // question
                Map<String, Integer> homeResults = finalResult.get(match.getHomeTeam().getTeamName());
                homeResults.put(winnerKey, homeResults.getOrDefault(winnerKey, 0) + 1);

                finalResult.putIfAbsent(match.getAwayTeam().getTeamName(), new HashMap<>());
                Map<String, Integer> awayResults = finalResult.get(match.getAwayTeam().getTeamName());
                awayResults.put(loserKey, awayResults.getOrDefault(loserKey, 0) + 1);
            } else if (match.getHomeTeamScore() < match.getAwayTeamScore()) {

                finalResult.putIfAbsent(match.getHomeTeam().getTeamName(), new HashMap<>());
                Map<String, Integer> homeResults = finalResult.get(match.getHomeTeam().getTeamName());
                homeResults.put(loserKey, homeResults.getOrDefault(loserKey, 0) + 1);


                finalResult.putIfAbsent(match.getAwayTeam().getTeamName(), new HashMap<>());
                Map<String, Integer> awayResults = finalResult.get(match.getAwayTeam().getTeamName());
                awayResults.put(winnerKey, awayResults.getOrDefault(winnerKey, 0) + 1);
            } else {
                finalResult.putIfAbsent(match.getHomeTeam().getTeamName(), new HashMap<>());
                Map<String, Integer> homeResults = finalResult.get(match.getHomeTeam().getTeamName());
                homeResults.put(drawerKey, homeResults.getOrDefault(drawerKey, 0) + 1);

                finalResult.putIfAbsent(match.getAwayTeam().getTeamName(), new HashMap<>());
                Map<String, Integer> awayResults = finalResult.get(match.getAwayTeam().getTeamName());
                awayResults.put(drawerKey, awayResults.getOrDefault(drawerKey, 0) + 1);
            }
        }
        for (Map.Entry<String, Map<String, Integer>> entry : finalResult.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    public void getResults2(int theId) {
        List<Match> allMatches = leagueDAO.findAllMatchesByLeagueId(theId);

        Map<String, TeamResult> finalResult = new HashMap<>();

        for (Match match : allMatches) {

            String homeTeam = match.getHomeTeam().getTeamName();
            String awayTeam = match.getAwayTeam().getTeamName();

            finalResult.putIfAbsent(homeTeam, new TeamResult());
            finalResult.putIfAbsent(awayTeam, new TeamResult());

            if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
                finalResult.get(homeTeam).increment(Result.WIN);
                finalResult.get(awayTeam).increment(Result.LOSS);
            } else if (match.getHomeTeamScore() < match.getAwayTeamScore()) {
                finalResult.get(homeTeam).increment(Result.LOSS);
                finalResult.get(awayTeam).increment(Result.WIN);
            } else {
                finalResult.get(homeTeam).increment(Result.DRAW);
                finalResult.get(awayTeam).increment(Result.DRAW);
            }
        }
        for (Map.Entry<String, TeamResult> entry : finalResult.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }
}
