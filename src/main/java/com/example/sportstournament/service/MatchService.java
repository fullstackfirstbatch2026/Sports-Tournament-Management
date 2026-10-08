package com.example.sportstournament.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.sportstournament.entity.Match;
import com.example.sportstournament.repository.MatchRepository;
import com.example.sportstournament.util.Rows;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // JOIN
    public List<Map<String, Object>> getMatchDetails() {
        return Rows.toMaps(matchRepository.getMatchDetails(),
                "match_id", "match_date", "venue", "status",
                "team1_id", "team1", "team1_city",
                "team2_id", "team2", "team2_city",
                "team1_score", "team2_score");
    }

    public Match saveMatch(Match match) {
        if (match.getTeam1Id() == null || match.getTeam2Id() == null || match.getMatchDate() == null) {
            throw new IllegalArgumentException("team1_id, team2_id and match_date are required");
        }
        if (match.getTeam1Id().equals(match.getTeam2Id())) {
            throw new IllegalArgumentException("A team cannot play itself");
        }
        match.setStatus("SCHEDULED");
        return matchRepository.save(match);
    }

    public void deleteMatch(Integer id) {
        matchRepository.deleteById(id);
    }
}
