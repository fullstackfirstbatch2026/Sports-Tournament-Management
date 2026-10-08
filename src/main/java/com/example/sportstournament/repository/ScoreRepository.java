package com.example.sportstournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sportstournament.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    // =========================================================
    // CALL MYSQL STORED PROCEDURE: add_match_score
    // (the trigger then completes the match and updates points)
    // =========================================================
    @Modifying
    @Query(value = "CALL add_match_score(:matchId, :teamId, :score)", nativeQuery = true)
    void addMatchScore(
        @Param("matchId") Integer matchId,
        @Param("teamId") Integer teamId,
        @Param("score") Integer score
    );
}
