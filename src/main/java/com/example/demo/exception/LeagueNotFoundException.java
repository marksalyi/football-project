package com.example.demo.exception;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(int id) {
        super("League not found with id " + id);
    }
}
