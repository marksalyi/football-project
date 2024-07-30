package com.example.demo.entity;

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
    public String toString() {
        return "Wins: " + wins + ", Losses: " + losses + ", Draws: " + draws;
    }
}
