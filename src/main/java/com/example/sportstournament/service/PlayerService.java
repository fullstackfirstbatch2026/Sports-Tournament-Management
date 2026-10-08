package com.example.sportstournament.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.sportstournament.entity.Player;
import com.example.sportstournament.repository.PlayerRepository;
import com.example.sportstournament.util.Rows;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // JOIN
    public List<Map<String, Object>> getPlayerDetails() {
        return Rows.toMaps(playerRepository.getPlayerDetails(),
                "player_id", "player_name", "team_name", "position", "jersey_number");
    }

    public Player savePlayer(Player player) {
        if (player.getPlayerName() == null || player.getPlayerName().isBlank() || player.getTeamId() == null) {
            throw new IllegalArgumentException("player_name and team_id are required");
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}
