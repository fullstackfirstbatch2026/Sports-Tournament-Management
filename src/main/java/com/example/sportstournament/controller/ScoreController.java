package com.example.sportstournament.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.sportstournament.entity.Score;
import com.example.sportstournament.service.ScoreService;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    // PROCEDURE
    @PostMapping
    public Map<String, Object> addScore(@RequestBody Score score) {
        return scoreService.addMatchScore(score);
    }
}
