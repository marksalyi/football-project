package com.example.demo.dao;

import com.example.demo.entity.Match;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchDAOImpl implements MatchDAO {

    private EntityManager entityManager;

    @Autowired
    public MatchDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public Match save(Match match) {
        return entityManager.merge(match);
    }
}
