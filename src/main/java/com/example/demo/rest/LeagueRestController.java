package com.example.demo.rest;

import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;
import com.example.demo.service.LeagueService;
import com.example.demo.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 3 controller létrehozása és annak szétszedése (team, match, league)
// endpoint visszaadja az adott ligaban szereplő csapatokat pont szerint sorrendben

@RestController
@RequestMapping("/api/league")
public class LeagueRestController {

    public LeagueService leagueService;

    public MatchService matchService;

    public LeagueRestController(LeagueService theLeagueService, MatchService theMatchService) {
        leagueService = theLeagueService;
        matchService = theMatchService;
    }

    @GetMapping("/leagues")
    public List<League> findAll() {
        return leagueService.findAll();
    }

    @GetMapping("/getleague/{leagueId}")
    public League getLeague(@PathVariable int leagueId) {
        League dbLeague = leagueService.findById(leagueId);

        return dbLeague;
    }

    @PostMapping("/newleague")
    public League createLeague(@RequestBody League theLeague) {
        theLeague.setId(0);

        League dbLeague = leagueService.save(theLeague);

        return dbLeague;
    }


    @PutMapping("/updateleague")
    public void updateLeague(@RequestBody League theLeague) {
        leagueService.update(theLeague);
    }

    @DeleteMapping("/deleteleague/{deleteId}")
    public void deleteLeague(@PathVariable int deleteId) {
        leagueService.deleteById(deleteId);
    }

    @GetMapping("/leagues/{leagueId}/teams")
    public List<FootballTeam> getTeamsByPointsInLeague(@PathVariable int leagueId) {
        League league = leagueService.findById(leagueId);
        if (league == null) {
            throw new RuntimeException("League not found - " + leagueId);
        }

        List<Match> matches = matchService.findByHomeTeamLeagueOrAwayTeamLeague(league, league);

        Map<FootballTeam, Integer> teamPoints = new HashMap<>();

        // Initialize the points for each team in the league
        for (FootballTeam team : league.getFootballTeams()) {
            teamPoints.put(team, 0);
        }

        // Calculate points based on match results
        for (Match match : matches) {
            FootballTeam homeTeam = match.getHomeTeam();
            FootballTeam awayTeam = match.getAwayTeam();
            int homeScore = match.getHomeTeamScore();
            int awayScore = match.getAwayTeamScore();

            if (homeScore > awayScore) {
                teamPoints.put(homeTeam, teamPoints.get(homeTeam) + 3);
            } else if (homeScore < awayScore) {
                teamPoints.put(awayTeam, teamPoints.get(awayTeam) + 3);
            } else {
                teamPoints.put(homeTeam, teamPoints.get(homeTeam) + 1);
                teamPoints.put(awayTeam, teamPoints.get(awayTeam) + 1);
            }
        }

        // Sort teams by points in descending order
        return teamPoints.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    @GetMapping("/leagues/{leagueId}/teams2")
    public Map<String, FootballTeam> getTeamsByPointsInLeague2(@PathVariable int leagueId) {
        League league = leagueService.findById(leagueId);
        if (league == null) {
            throw new RuntimeException("League not found - " + leagueId);
        }

        List<Match> matches = matchService.findByLeague(league);

        Map<String, FootballTeam> latestTeamsByResult = new HashMap<>();

        Match latestHomeWin = matchService.findLatestMatchByResult(matches, Result.HOME);
        if (latestHomeWin != null) {
            latestTeamsByResult.put("HOME", latestHomeWin.getHomeTeam());
        }

        Match latestLoss = matchService.findLatestMatchByResult(matches, Result.LOSS);
        if (latestLoss != null) {
            latestTeamsByResult.put("LOSS", latestLoss.getAwayTeam());
        }

        Match latestDraw = matchService.findLatestMatchByResult(matches, Result.DRAW);
        if (latestDraw != null) {
            latestTeamsByResult.put("DRAW", latestDraw.getHomeTeam());  // vagy awayTeam
        }

        return latestTeamsByResult;
    }
}
