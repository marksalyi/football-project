package com.example.demo.rest;


import com.example.demo.rest.dto.MatchRequest;
import com.example.demo.rest.dto.MatchResponse;
import com.example.demo.service.MatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchRestController {

    public MatchService matchService;

    public MatchRestController(MatchService theMatchService){
        matchService = theMatchService;
    }

    @PostMapping("/newmatch")
    public MatchResponse createMatch(@RequestBody MatchRequest theMatch){

        MatchResponse dbMatch = matchService.save(theMatch);
        return dbMatch;
    }


    //Task: 1. new column Result - String, Enum
    //2. Service.save() - calculate value of 1. column
    // 3. Mapper for Match and request and response
    // 3. implement getTeamsByPointsInLeague2 based on new column
}
