package com.example.sportstournament.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scores", uniqueConstraints = @UniqueConstraint(columnNames = { "match_id", "team_id" }))
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Integer scoreId;

    @Column(name = "match_id", nullable = false)
    private Integer matchId;

    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    @Column(name = "score", nullable = false)
    private Integer score;

    public Score() {
    }

    public Integer getScoreId() { return scoreId; }
    public void setScoreId(Integer scoreId) { this.scoreId = scoreId; }
    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
