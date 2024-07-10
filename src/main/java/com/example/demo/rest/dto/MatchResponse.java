package com.example.demo.rest.dto;

import com.example.demo.entity.FootballTeam;

public class MatchResponse {

    private FootballTeam homeTeam;


    private FootballTeam awayTeam;

    private int homeTeamScore;

    private int awayTeamScore;

    public MatchResponse(){

    }
    public MatchResponse(FootballTeam homeTeam, FootballTeam awayTeam, int homeTeamScore, int awayTeamScore) {
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

    public void setHomeTeam(FootballTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(FootballTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
