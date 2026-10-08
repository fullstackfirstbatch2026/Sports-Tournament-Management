package com.example.sportstournament.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.sportstournament.entity.Match;
import com.example.sportstournament.service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // JOIN
    @GetMapping
    public List<Map<String, Object>> getMatchDetails() {
        return matchService.getMatchDetails();
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchService.saveMatch(match);
    }

    @DeleteMapping("/{id}")
    public String deleteMatch(@PathVariable Integer id) {
        matchService.deleteMatch(id);
        return "Match deleted successfully";
    }
}
