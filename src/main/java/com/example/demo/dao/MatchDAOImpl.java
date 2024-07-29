package com.example.demo.dao;

import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Match2;
import com.example.demo.entity.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchDAOImpl implements MatchDAO
{

    private EntityManager entityManager;

    @Autowired
    public MatchDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public Match save(Match match) {
        return entityManager.merge(match);
    }

    @Override
    public Match2 save2(Match2 match) {
        return entityManager.merge(match);
    }

    @Override
    public List<Match> findByHomeTeamLeagueOrAwayTeamLeague(League homeLeague, League awayLeague) {
        TypedQuery<Match> query = entityManager.createQuery(
                "from Match where homeTeam.league = :league or awayTeam.league = :league", Match.class);
        query.setParameter("league", homeLeague);
        return query.getResultList();
    }

    @Override
    public List<Match> findByLeague(League league) {
        TypedQuery<Match> query = entityManager.createQuery(
                "from Match m where m.homeTeam.league = :league or m.awayTeam.league = :league order by m.id desc", Match.class);
        query.setParameter("league", league);
        return query.getResultList();
    }

    @Override
    public Match findLatestMatchByResult(List<Match> matches, Result result) {
            return matches.stream()
                    .filter(match -> match.getResult() == result)
                    .findFirst()
                    .orElse(null);

    }

    public List<Match> findByTeam(FootballTeam team){
        TypedQuery<Match> query = entityManager.createQuery(
                "from Match m where m.homeTeam.teamName = :team or m.awayTeam.teamName = :team order by m.id desc", Match.class);
        query.setParameter("team", team.getTeamName());
        return query.getResultList();
    }

    @Override
    public List<Match2> findByTeam2(FootballTeam team) {
        TypedQuery<Match2> query = entityManager.createQuery(
                "from Match2 m where m.homeTeam.teamName = :team or m.awayTeam.teamName = :team order by m.id desc", Match2.class);
        query.setParameter("team", team.getTeamName());
        return query.getResultList();
    }
}
