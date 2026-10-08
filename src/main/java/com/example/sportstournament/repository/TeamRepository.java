package com.example.sportstournament.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sportstournament.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    // Standings: highest points first
    List<Team> findAllByOrderByPointsDescTeamNameAsc();

    // =========================================================
    // SUBQUERY: TEAMS WHOSE TOTAL SCORE IS ABOVE THE AVERAGE
    // =========================================================
    @Query(value = """
        SELECT t.team_id, t.team_name, SUM(s.score) AS total_score
        FROM teams t
        JOIN scores s ON s.team_id = t.team_id
        GROUP BY t.team_id, t.team_name
        HAVING SUM(s.score) > (
            SELECT AVG(x.total)
            FROM (
                SELECT SUM(score) AS total
                FROM scores
                GROUP BY team_id
            ) x
        )
        ORDER BY total_score DESC
        """, nativeQuery = true)
    List<Object[]> getTeamsAboveAverage();

    // =========================================================
    // CALL MYSQL FUNCTION: calc_team_total_points
    // =========================================================
    @Query(value = "SELECT calc_team_total_points(:teamId)", nativeQuery = true)
    Integer calculateTeamPoints(@Param("teamId") Integer teamId);
}
