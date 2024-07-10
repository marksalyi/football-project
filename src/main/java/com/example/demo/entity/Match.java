package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="football_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private FootballTeam homeTeam;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private FootballTeam awayTeam;

    @JoinColumn(name = "home_team_score")
    private int homeTeamScore;

    @JoinColumn(name = "away_team_score")
    private int awayTeamScore;

    @JoinColumn(name = "result")
    @Enumerated(EnumType.STRING)
    private Result result;



    public Match() {

    }

    public Match(FootballTeam homeTeam, FootballTeam awayTeam, int homeTeamScore, int awayTeamScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FootballTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FootballTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
