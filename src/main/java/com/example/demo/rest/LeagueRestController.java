package com.example.demo.rest;

import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Match2;
import com.example.demo.entity.Result;
import com.example.demo.service.LeagueService;
import com.example.demo.service.Match2Service;
import com.example.demo.service.MatchService;
import com.example.demo.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    public TeamService teamService;

    public Match2Service match2Service;


    public LeagueRestController(LeagueService theLeagueService, MatchService theMatchService, TeamService theTeamService, Match2Service theMatch2Service) {
        leagueService = theLeagueService;
        matchService = theMatchService;
        teamService = theTeamService;
        match2Service = theMatch2Service;
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

    // legregebb ota result enum alapjan
    @GetMapping("/leagues/{leagueId}")
    public FootballTeam getLatestByResult(@PathVariable int leagueId, @RequestParam Result result){
        League league = leagueService.findById(leagueId);
        List<Match> matches = matchService.findByHomeTeamLeagueOrAwayTeamLeague(league, league);

        FootballTeam team = null;
        LocalDate date = LocalDate.of(2023, 1, 11);

        for(Match match : matches){
            if(result == Result.WIN && match.getDate().isBefore(date)){

                team = teamService.findById(match.getHomeTeam().getId());
                date = match.getDate();

            } else if (result == Result.LOSS && match.getDate().isBefore(date)){
                System.out.println("LOSSSSSSSSSSSSSSSSS" + teamService.findById(match.getAwayTeam().getId()));
                team = teamService.findById(match.getAwayTeam().getId());
                System.out.println("TEAMNAME" + team.getTeamName());
                date = match.getDate();

            } else if(result == Result.DRAW && match.getDate().isBefore(date)) {
                team = teamService.findById(match.getHomeTeam().getId());
            }
        }
        System.out.println("before return " + team.getTeamName());
        return team;
    }

    @GetMapping("/leagues2/{leagueId}")
    public Match getAllmatchesByTeam(@PathVariable int leagueId, @RequestParam Result result){
        // TODO input RESULT should be home draw away
        League league = leagueService.findById(leagueId);
        Map<FootballTeam, List<Match>> matchesByTeam = new HashMap<>();
        Match finalMatch = null;
        for(FootballTeam team : league.getFootballTeams()) {
            List<Match> relatedMatch = matchService.findByTeam(team);
            matchesByTeam.put(team, relatedMatch);
        }

        for(Map.Entry<FootballTeam, List<Match>> entry : matchesByTeam.entrySet()){
             entry.getValue().sort(null);

             for(Match value : entry.getValue()){
                 //TODO decide what kind of result is the MATCH
                 //value.getAwayTeamScore() > value.getHomeTeamScore()
                 if (value.getResult() == result){
                     //finalMatch = value;
                     return value;
                 }
             }
        }
        return finalMatch;
    }



    @GetMapping("/leagues3/{leagueId}")
    public Match2 getAllmatchesByTeam2(@PathVariable int leagueId,@RequestParam String result){
        // TODO input RESULT should be home draw away
        League league = leagueService.findById(leagueId);
        Map<FootballTeam, List<Match>> matchesByTeam = new HashMap<>();
        Match2 finalMatch = new Match2();
        for(FootballTeam team : league.getFootballTeams()) {
            List<Match> relatedMatch = matchService.findByTeam(team);
            matchesByTeam.put(team, relatedMatch);
        }

        for(Map.Entry<FootballTeam, List<Match>> entry : matchesByTeam.entrySet()){
            entry.getValue().sort(null);

            for(Match value : entry.getValue()){
                if (value.getHomeTeamScore() > value.getAwayTeamScore() && result.equals("HOME")){
                    finalMatch.setHomeTeam(value.getHomeTeam());
                    finalMatch.setAwayTeam(value.getAwayTeam());
                    finalMatch.setHomeTeamScore(value.getHomeTeamScore());
                    finalMatch.setAwayTeamScore(value.getAwayTeamScore());
                    finalMatch.setDate(value.getDate());
                    Match2 dbMatch = match2Service.save2(finalMatch);
                    return dbMatch;
                } else if(value.getHomeTeamScore() > value.getAwayTeamScore() && result.equals("AWAY")){
                    finalMatch.setHomeTeam(value.getHomeTeam());
                    finalMatch.setAwayTeam(value.getAwayTeam());
                    finalMatch.setHomeTeamScore(value.getHomeTeamScore());
                    finalMatch.setAwayTeamScore(value.getAwayTeamScore());
                    finalMatch.setDate(value.getDate());
                    Match2 dbMatch = match2Service.save2(finalMatch);
                    return dbMatch;
                } else if(value.getHomeTeamScore() == value.getAwayTeamScore() && result.equals("DRAW")){
                    finalMatch.setHomeTeam(value.getHomeTeam());
                    finalMatch.setAwayTeam(value.getAwayTeam());
                    finalMatch.setHomeTeamScore(value.getHomeTeamScore());
                    finalMatch.setAwayTeamScore(value.getAwayTeamScore());
                    finalMatch.setDate(value.getDate());
                    Match2 dbMatch = match2Service.save2(finalMatch);
                    return dbMatch;
                }
            }
        }
        return finalMatch;
    }

    // endpoint result alapjan - result neve
    // adjon vissza egy olyan adatstrukturat ami vissza adja hogy egy csapat hany w,l,d

    @GetMapping("/results/{leagueId}")
    public void getTeamResults(@PathVariable int leagueId){
        // megtalalni egy adott ligat
        League league = leagueService.findById(leagueId);
        // map letrehozasa aminel a kulcs a csapat es az ertek pedig az osszes meccs
        Map<FootballTeam, List<Match>> matchesByTeam = new HashMap<>(); // TODO is List the proper Collection implemenation to use

        for (FootballTeam team : league.getFootballTeams()){
            List<Match> relatedTeamMatches = matchService.findByTeam(team);
            matchesByTeam.put(team, relatedTeamMatches);
            //TODO what happens if somehow two team name is equal (or de we check this details while persisting a Match)
        }


        Map<String, Map<String, Integer>> finalResult = new HashMap<>();
        for (Map.Entry<FootballTeam, List<Match>> entry : matchesByTeam.entrySet()){
            Map<String, Integer> collectResult = new HashMap<>();
            System.out.println("elso for ciklus " + entry.getKey());
            Integer homeCounter = 0;
            Integer awayCounter = 0;
            Integer drawCounter = 0;
            for(Match matchByTeam : entry.getValue()){
                System.out.println("meccsek ");
                if(matchByTeam.getResult().toString().equals("WIN")){
                    homeCounter++;
                    collectResult.put("WIN",homeCounter);

                } else if(matchByTeam.getResult().toString().equals("LOSS")){
                    awayCounter++;
                    collectResult.put("LOSS",awayCounter);

                } else if(matchByTeam.getResult().toString().equals("DRAW")){
                    drawCounter++;
                    collectResult.put("DRAW", drawCounter);

                }

            }
            finalResult.put(entry.getKey().getTeamName(), collectResult);
            System.out.println("tedd bele");
        }

        for (Map.Entry<String, Map<String, Integer>> entry : finalResult.entrySet()){
            System.out.println("Football team: " + entry.getKey() + " results " + entry.getValue());
        }
    }

    @GetMapping("/results2/{leagueId}")
    public void getTeamResults2(@PathVariable int leagueId){
        // TODO only validation in REST layer
        // logic belongs to service layer
        // first grab all the matches by a league
        // based on the home and away id and scores decide is it a win or loss for a team and save it to a list or a map
       leagueService.getResults(leagueId);

            }
        }

