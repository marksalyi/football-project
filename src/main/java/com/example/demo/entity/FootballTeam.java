package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="football_team")
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="team_name")
    private String teamName;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="league_id", referencedColumnName = "id")
    private League league;

    @JsonManagedReference
    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;

    @JsonManagedReference
    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    public FootballTeam(){

    }

    public FootballTeam(String teamName, League league) {
        this.teamName = teamName;
        this.league = league;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(List<Match> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", league=" + league +
                '}';
    }
}
