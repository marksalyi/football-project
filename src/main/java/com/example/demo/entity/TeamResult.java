package com.example.demo.entity;

import java.util.Objects;

public class TeamResult {
    private int wins;
    private int losses;
    private int draws;

    public void increment(Result result) {
        switch (result) {
            case WIN -> wins++;
            case LOSS -> losses++;
            case DRAW -> draws++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamResult that = (TeamResult) o;
        return wins == that.wins && losses == that.losses && draws == that.draws;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, losses, draws);
    }

    @Override
    public String toString() {
        return "Wins: " + wins + ", Losses: " + losses + ", Draws: " + draws;
    }
}
