package com.example.demo.entity;

// GET http://localhost:8080/api/league/leagues/1/teams3?result=HOME
public class Statistics {

    private int id;

    private String name;

    private String result;

    public Statistics() {

    }

    public Statistics(int id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    public Statistics(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
