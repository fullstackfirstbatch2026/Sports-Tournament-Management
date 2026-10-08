package com.example.sportstournament.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sportstournament.entity.Score;
import com.example.sportstournament.repository.ScoreRepository;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // PROCEDURE
    @Transactional
    public Map<String, Object> addMatchScore(Score score) {
        if (score.getMatchId() == null || score.getTeamId() == null || score.getScore() == null) {
            throw new IllegalArgumentException("match_id, team_id and score are required");
        }
        scoreRepository.addMatchScore(score.getMatchId(), score.getTeamId(), score.getScore());
        return Map.of("message", "Score added");
    }
}
