package com.example.demo.rest;

import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.FootballTeam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamRestController {

    public TeamDAO teamDAO;

    public TeamRestController(TeamDAO theTeamDAO){
        teamDAO = theTeamDAO;
    }

    @GetMapping("/getteam/{teamId}")
    public FootballTeam getTeam(@PathVariable int teamId){
        FootballTeam dbFootballTeam = teamDAO.findById(teamId);

        return dbFootballTeam;
    }

    @PostMapping("/newteam")
    public FootballTeam createTeam(@RequestBody FootballTeam theFootballTeam){
        theFootballTeam.setId(0);

        FootballTeam dbFootballTeam = teamDAO.save(theFootballTeam);

        return dbFootballTeam;
    }

    @PutMapping("/updateteam")
    public void updateTeam(@RequestBody FootballTeam theFootballTeam){
        teamDAO.update(theFootballTeam);
    }

    @DeleteMapping("/deleteteam/{deleteId}")
    public void deleteTeam(@PathVariable int deleteId){
        teamDAO.delete(deleteId);
    }
}
