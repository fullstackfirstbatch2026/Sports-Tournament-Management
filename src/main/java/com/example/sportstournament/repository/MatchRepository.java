package com.example.sportstournament.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sportstournament.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    // =========================================================
    // JOIN: MATCH DETAILS WITH BOTH TEAMS AND THEIR SCORES
    // =========================================================
    @Query(value = """
        SELECT
            m.match_id,
            DATE_FORMAT(m.match_date, '%Y-%m-%d') AS match_date,
            m.venue,
            m.status,
            t1.team_id   AS team1_id,
            t1.team_name AS team1,
            t1.city      AS team1_city,
            t2.team_id   AS team2_id,
            t2.team_name AS team2,
            t2.city      AS team2_city,
            s1.score     AS team1_score,
            s2.score     AS team2_score
        FROM matches m
        JOIN teams t1 ON m.team1_id = t1.team_id
        JOIN teams t2 ON m.team2_id = t2.team_id
        LEFT JOIN scores s1 ON s1.match_id = m.match_id AND s1.team_id = m.team1_id
        LEFT JOIN scores s2 ON s2.match_id = m.match_id AND s2.team_id = m.team2_id
        ORDER BY m.match_date, m.match_id
        """, nativeQuery = true)
    List<Object[]> getMatchDetails();
}
