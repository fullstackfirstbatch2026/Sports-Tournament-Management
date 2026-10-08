package com.example.sportstournament.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @Column(name = "city")
    private String city;

    @Column(name = "coach")
    private String coach;

    @Column(name = "points", columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer points = 0;

    public Team() {
    }

    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
}
