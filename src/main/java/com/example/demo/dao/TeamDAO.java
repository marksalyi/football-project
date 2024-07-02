package com.example.demo.dao;

import com.example.demo.entity.FootballTeam;

public interface TeamDAO {

    FootballTeam findById(int theId);

    FootballTeam save(FootballTeam footballTeam);

    void update(FootballTeam footballTeam);

    void delete(int theId);

}