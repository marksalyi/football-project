package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="league_name")
    private String leagueName;

    @JsonManagedReference
    @OneToMany(mappedBy = "league", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<FootballTeam> footballTeams;

    public League(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Set<FootballTeam> getFootballTeams() {
        return footballTeams;
    }

    public void setFootballTeams(Set<FootballTeam> footballTeams) {
        this.footballTeams = footballTeams;
    }

    public void add(FootballTeam footballTeam){
        if(footballTeams == null){
            footballTeams = new HashSet<>();
        }
            footballTeams.add(footballTeam);
        footballTeam.setLeague(this);
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", leagueName='" + leagueName + '\'' +
                ", footballTeams=" + footballTeams +
                '}';
    }
}
