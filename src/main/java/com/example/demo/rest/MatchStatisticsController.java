package com.example.demo.rest;

import com.example.demo.entity.MatchStatistics;
import com.example.demo.service.MatchStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class MatchStatisticsController {

    public MatchStatisticsService matchStatisticsService;

    public MatchStatisticsController(MatchStatisticsService theMatchStatisticsService){
        this.matchStatisticsService = theMatchStatisticsService;
    }

    @PostMapping("/save/{matchId}")
    public void saveMatchStatistics(@PathVariable int matchId , @RequestBody MatchStatistics matchStatistics){
        matchStatisticsService.save(matchId, matchStatistics);

    }

    @GetMapping("/filter")
    public List<MatchStatistics> filterMatchStatistics(
            @RequestParam String column,
            @RequestParam String condition,
            @RequestParam int value) {
        return matchStatisticsService.filterMatchStatistics(column, condition, value);
    }
    }
