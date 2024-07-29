package com.example.demo.dao;

import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueDAOImpl
        implements LeagueDAO
{
    private EntityManager entityManager;


    @Autowired
    public LeagueDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }


    @Override
    public List<League> findAll() {

        return entityManager.createQuery("SELECT u FROM League u").getResultList();
    }

    @Override
    public League findById(int theId) {
        return entityManager.find(League.class, theId);
    }

    @Override
    public League save(League theLeague) {
        entityManager.persist(theLeague);
        return theLeague;
    }

    @Override
    public void deleteById(int theId) {
        League league = entityManager.find(League.class, theId);
        entityManager.remove(league);
    }

    @Override
    public void update(League theLeague) {
        entityManager.merge(theLeague);
    }

    @Override
    public List<Match> findAllMatchesByLeagueId(int theId) {

        TypedQuery<Match> query = entityManager.createQuery(
                "SELECT m FROM Match m WHERE m.league.id = :leagueId", Match.class);
        query.setParameter("leagueId", theId);

        List<Match> matches = query.getResultList();

        return matches;
    }
}
