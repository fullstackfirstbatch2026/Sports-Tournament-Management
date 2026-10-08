package com.example.sportstournament.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.sportstournament.entity.Team;
import com.example.sportstournament.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    // SUBQUERY
    @GetMapping("/above-average")
    public List<Map<String, Object>> getTeamsAboveAverage() {
        return teamService.getTeamsAboveAverage();
    }

    // FUNCTION
    @GetMapping("/{id}/points")
    public Map<String, Object> getTeamPoints(@PathVariable Integer id) {
        return teamService.calculateTeamPoints(id);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{id}")
    public String deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
        return "Team deleted successfully";
    }
}
