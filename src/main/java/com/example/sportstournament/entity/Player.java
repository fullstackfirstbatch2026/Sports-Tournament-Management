package com.example.sportstournament.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    @Column(name = "position")
    private String position;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

    public Player() {
    }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }
}
