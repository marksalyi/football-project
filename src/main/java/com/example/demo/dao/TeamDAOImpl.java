package com.example.demo.dao;

import com.example.demo.entity.FootballTeam;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeamDAOImpl implements TeamDAO {

    private EntityManager entityManager;

    @Autowired
    public TeamDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public FootballTeam findById(int theId) {
        FootballTeam dbFootballTeam = entityManager.find(FootballTeam.class, theId);

        return dbFootballTeam;
    }

    @Override
    public FootballTeam save(FootballTeam footballTeam) {
        FootballTeam dbFootballTeam =  entityManager.merge(footballTeam);
        return dbFootballTeam;
    }

    @Override
    public void update(FootballTeam footballTeam) {
        entityManager.merge(footballTeam);
    }

    @Override
    public void delete(int theId) {
        FootballTeam dbFootballTeam = entityManager.find(FootballTeam.class, theId);
        entityManager.remove(dbFootballTeam);
    }
}
