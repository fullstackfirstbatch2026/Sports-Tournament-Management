package com.example.sportstournament.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Integer matchId;

    @Column(name = "team1_id", nullable = false)
    private Integer team1Id;

    @Column(name = "team2_id", nullable = false)
    private Integer team2Id;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "venue")
    private String venue;

    @Column(name = "status", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED'")
    private String status = "SCHEDULED";

    public Match() {
    }

    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }
    public Integer getTeam1Id() { return team1Id; }
    public void setTeam1Id(Integer team1Id) { this.team1Id = team1Id; }
    public Integer getTeam2Id() { return team2Id; }
    public void setTeam2Id(Integer team2Id) { this.team2Id = team2Id; }
    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
