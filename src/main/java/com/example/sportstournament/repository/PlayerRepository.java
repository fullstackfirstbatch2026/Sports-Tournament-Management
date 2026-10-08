package com.example.sportstournament.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sportstournament.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // =========================================================
    // JOIN: PLAYERS WITH THEIR TEAM NAME
    // =========================================================
    @Query(value = """
        SELECT p.player_id, p.player_name, t.team_name, p.position, p.jersey_number
        FROM players p
        JOIN teams t ON p.team_id = t.team_id
        ORDER BY t.team_name, p.player_name
        """, nativeQuery = true)
    List<Object[]> getPlayerDetails();
}
