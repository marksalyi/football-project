package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="football_match")
public class Match implements Comparable<Match>{

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

    @Column(name = "home_team_score")
    private int homeTeamScore;

    @Column(name = "away_team_score")
    private int awayTeamScore;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private Result result;
    @Column(name = "match_date")
    private LocalDate date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public int compareTo(Match o) {
        return this.date.compareTo(o.date);}
}
