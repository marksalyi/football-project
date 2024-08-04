package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "match_statistics")
public class MatchStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private int id;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match matchId;
    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "corners")
    private int corners;

    @Column(name = "home_possession")
    private BigDecimal homePossession;

    @Column(name =  "away_possession")
    private BigDecimal awayPossession;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getMatchId() {
        return matchId;
    }

    public void setMatchId(Match matchId) {
        this.matchId = matchId;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }

    public BigDecimal getHomePossession() {
        return homePossession;
    }

    public void setHomePossession(BigDecimal homePossession) {
        this.homePossession = homePossession;
    }

    public BigDecimal getAwayPossession() {
        return awayPossession;
    }

    public void setAwayPossession(BigDecimal awayPossession) {
        this.awayPossession = awayPossession;
    }
}
