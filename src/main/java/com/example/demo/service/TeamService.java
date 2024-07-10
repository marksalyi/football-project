package com.example.demo.service;

import com.example.demo.entity.FootballTeam;

public interface TeamService {
    FootballTeam findById(int theId);

    FootballTeam save(FootballTeam footballTeam);

    void update(FootballTeam footballTeam);

    void delete(int theId);
}
