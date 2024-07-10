package com.example.demo.service;

import com.example.demo.entity.League;

import java.util.List;

public interface LeagueService {

    List<League> findAll();

    League findById(int theId);

    League save(League theLeague);

    void deleteById(int theId);

    void update(League theLeague);
}
