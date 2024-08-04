package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="football_team")
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="team_name")
    private String teamName;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "league_football_team",
            joinColumns = @JoinColumn(name = "football_team_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id")
    )
    private Set<League> leagues = new HashSet<>();


    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;


    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    public FootballTeam(){

    }

    public FootballTeam(String teamName, Set<League> league) {
        this.teamName = teamName;
        this.leagues = league;
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

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
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

    public void addLeague(League theLeague){
        leagues.add(theLeague);
        theLeague.getFootballTeams().add(this);
    }
    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", leagues=" + leagues +
                ", homeMatches=" + homeMatches +
                ", awayMatches=" + awayMatches +
                '}';
    }
}
