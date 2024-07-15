package com.example.demo.dao;

import com.example.demo.entity.League;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LeagueDAOImpl implements LeagueDAO {

    private EntityManager entityManager;

    @Autowired
    public LeagueDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<League> findAll() {

        TypedQuery<League> query =entityManager.createQuery("from League", League.class);

        List<League> leagues = query.getResultList();

        return leagues;
    }

    @Override
    public League findById(int theId) {
        League theLeague = entityManager.find(League.class, theId);
        return theLeague;
    }

    @Override
    public League save(League theLeague) {
        League dbLeague = entityManager.merge(theLeague);
        return dbLeague;
    }

    @Override
    public void deleteById(int theId) {
        League theLeague = entityManager.find(League.class, theId);
        entityManager.remove(theLeague);
    }

    @Override
    public void update(League theLeague) {
        entityManager.merge(theLeague);
    }
}
