package com.example.demo.dao;


import com.example.demo.entity.MatchStatistics;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchStatisticsImpl implements MatchStatisticsDAO {

    private EntityManager entityManager;

    @Autowired
    public MatchStatisticsImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }


    @Override
    public void save(MatchStatistics theMatchStatistics) {
        entityManager.merge(theMatchStatistics);
    }

    @Override
    public List<MatchStatistics> filterMatchStatistics(String column, String condition, int value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MatchStatistics> query = cb.createQuery(MatchStatistics.class);
        Root<MatchStatistics> root = query.from(MatchStatistics.class);

        // Convert column name to match Java entity property names
        String javaColumn = convertToJavaProperty(column);

        Predicate predicate;
        if ("greater".equalsIgnoreCase(condition)) {
            predicate = cb.greaterThan(root.get(javaColumn), value);
        } else if ("less".equalsIgnoreCase(condition)) {
            predicate = cb.lessThan(root.get(javaColumn), value);
        } else {
            throw new IllegalArgumentException("Invalid condition: " + condition);
        }

        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    private String convertToJavaProperty(String column) {
        switch (column) {
            case "yellow_cards":
                return "yellowCards";
            case "red_cards":
                return "redCards";
            case "corners":
                return "corners";
            case "home_possession":
                return "homePossession";
            case "away_possession":
                return "awayPossession";
            default:
                throw new IllegalArgumentException("Unknown column: " + column);
        }
    }
}
