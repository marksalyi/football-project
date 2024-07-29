package com.example.demo.dao;

import com.example.demo.entity.Match2;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Match2DAOImpl implements Match2DAO{

    private EntityManager entityManager;

    @Autowired
    public Match2DAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public Match2 save2(Match2 match) {
        return entityManager.merge(match);
    }
}
