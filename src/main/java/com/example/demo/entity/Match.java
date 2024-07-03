package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="football_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private FootballTeam homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private FootballTeam awayTeam;

    private int homeTeamScore;

    private int awayTeamScore;

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
}
