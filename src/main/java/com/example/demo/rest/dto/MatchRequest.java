package com.example.demo.rest.dto;

import com.example.demo.entity.FootballTeam;

public class MatchRequest {


    private FootballTeam homeTeam;


    private FootballTeam awayTeam;

    private int homeTeamScore;

    private int awayTeamScore;

    public MatchRequest(){

    }
    public MatchRequest(FootballTeam homeTeam, FootballTeam awayTeam, int homeTeamScore, int awayTeamScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }
    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }
}
