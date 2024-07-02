package com.example.demo.rest;

import com.example.demo.dao.LeagueDAO;
import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DAO mindkettőre plusz domain specific crud method for all layer

@RestController
@RequestMapping("/api")
public class DemoRestController {

    public LeagueDAO leagueDAO;

    public TeamDAO teamDAO;

    public DemoRestController(LeagueDAO theLeagueDAO, TeamDAO theTeamDAO){
        leagueDAO = theLeagueDAO;
        teamDAO = theTeamDAO;
    }

    @GetMapping("/leagues")
    public List<League> findAll(){
        return leagueDAO.findAll();
    }

    @GetMapping("/league/{leagueId}")
    public League getLeague(@PathVariable int leagueId){
        League dbLeague = leagueDAO.findById(leagueId);

        return dbLeague;
    }

    @GetMapping("/team/{teamId}")
    public FootballTeam getTeam(@PathVariable int teamId){
        FootballTeam dbFootballTeam = teamDAO.findById(teamId);

        return dbFootballTeam;
    }

    @PostMapping("/newleague")
    public League createLeague(@RequestBody League theLeague){
        theLeague.setId(0);

        League dbLeague = leagueDAO.save(theLeague);

        return dbLeague;
    }

    @PostMapping("/newteam")
    public FootballTeam createTeam(@RequestBody FootballTeam theFootballTeam){
        theFootballTeam.setId(0);

        FootballTeam dbFootballTeam = teamDAO.save(theFootballTeam);

        return dbFootballTeam;
    }

    @PutMapping("/updateleague")
    public void updateLeague(@RequestBody League theLeague){
        leagueDAO.update(theLeague);
    }

    @PutMapping("/updateteam")
    public void updateTeam(@RequestBody FootballTeam theFootballTeam){
        teamDAO.update(theFootballTeam);
    }

    @DeleteMapping("/deleteleague/{deleteId}")
    public void deleteLeague(@PathVariable int deleteId){
        leagueDAO.deleteById(deleteId);
    }

    @DeleteMapping("/deleteteam/{deleteId}")
    public void deleteTeam(@PathVariable int deleteId){
        teamDAO.delete(deleteId);
    }
}
