package com.example.demo.rest;

import com.example.demo.entity.FootballTeam;
import com.example.demo.service.TeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamRestController {

    public TeamService teamService;

    public TeamRestController(TeamService theTeamService){
        teamService = theTeamService;
    }

    @GetMapping("/getteam/{teamId}")
    public FootballTeam getTeam(@PathVariable int teamId){
        FootballTeam dbFootballTeam = teamService.findById(teamId);

        return dbFootballTeam;
    }

    @PostMapping("/newteam")
    public FootballTeam createTeam(@RequestBody FootballTeam theFootballTeam){
        theFootballTeam.setId(0);

        FootballTeam dbFootballTeam = teamService.save(theFootballTeam);

        return dbFootballTeam;
    }

    @PutMapping("/updateteam")
    public void updateTeam(@RequestBody FootballTeam theFootballTeam){
        teamService.update(theFootballTeam);
    }

    @DeleteMapping("/deleteteam/{deleteId}")
    public void deleteTeam(@PathVariable int deleteId){
        teamService.delete(deleteId);
    }
}
