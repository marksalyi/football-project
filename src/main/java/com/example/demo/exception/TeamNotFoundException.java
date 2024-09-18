package com.example.demo.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(int id) {
        super("League not found with id " + id);
    }
}
