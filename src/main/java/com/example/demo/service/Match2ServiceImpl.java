package com.example.demo.service;

import com.example.demo.dao.Match2DAO;
import com.example.demo.dao.MatchDAO;
import com.example.demo.entity.Match2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Match2ServiceImpl implements Match2Service{

    @Autowired
    private Match2DAO matchDAO;

    public Match2ServiceImpl(Match2DAO matchDAO) {
        this.matchDAO = matchDAO;
    }
    @Override
    public Match2 save2(Match2 match) {
       return matchDAO.save2(match);
    }
}
