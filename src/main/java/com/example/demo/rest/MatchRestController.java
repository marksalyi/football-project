package com.example.demo.rest;


import com.example.demo.dao.LeagueDAO;
import com.example.demo.dao.MatchDAO;
import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.Match;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchRestController {

    public MatchDAO matchDAO;

    public MatchRestController(MatchDAO theMatchDAO){
        matchDAO = theMatchDAO;
    }

    @PostMapping("/newmatch")
    public Match createMatch(@RequestBody Match theMatch){

        Match dbMatch = matchDAO.save(theMatch);
        return dbMatch;
    }
}
