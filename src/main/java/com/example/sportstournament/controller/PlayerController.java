package com.example.sportstournament.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.sportstournament.entity.Player;
import com.example.sportstournament.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // JOIN
    @GetMapping
    public List<Map<String, Object>> getPlayerDetails() {
        return playerService.getPlayerDetails();
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
        return "Player deleted successfully";
    }
}
