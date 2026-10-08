package com.example.sportstournament.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.sportstournament.entity.Team;
import com.example.sportstournament.repository.TeamRepository;
import com.example.sportstournament.util.Rows;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAllByOrderByPointsDescTeamNameAsc();
    }

    public Team getTeamById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team saveTeam(Team team) {
        if (team.getTeamName() == null || team.getTeamName().isBlank()) {
            throw new IllegalArgumentException("team_name is required");
        }
        return teamRepository.save(team);
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    // SUBQUERY
    public List<Map<String, Object>> getTeamsAboveAverage() {
        return Rows.toMaps(teamRepository.getTeamsAboveAverage(), "team_id", "team_name", "total_score");
    }

    // FUNCTION
    public Map<String, Object> calculateTeamPoints(Integer teamId) {
        return Map.of("total_points", teamRepository.calculateTeamPoints(teamId));
    }
}
