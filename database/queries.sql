USE sports_tournament;
-- JOIN: match details with team information (MatchRepository.getMatchDetails)
SELECT m.match_id, t1.team_name AS team1, s1.score AS score1, s2.score AS score2, t2.team_name AS team2, m.status
FROM matches m JOIN teams t1 ON m.team1_id = t1.team_id JOIN teams t2 ON m.team2_id = t2.team_id
LEFT JOIN scores s1 ON s1.match_id = m.match_id AND s1.team_id = m.team1_id
LEFT JOIN scores s2 ON s2.match_id = m.match_id AND s2.team_id = m.team2_id;
-- SUBQUERY: teams above average (TeamRepository.getTeamsAboveAverage)
SELECT t.team_name, SUM(s.score) AS total_score FROM teams t JOIN scores s ON s.team_id = t.team_id
GROUP BY t.team_id, t.team_name
HAVING SUM(s.score) > (SELECT AVG(x.total) FROM (SELECT SUM(score) AS total FROM scores GROUP BY team_id) x);
-- PROCEDURE (ScoreRepository.addMatchScore):  CALL add_match_score(4, 2, 130);
-- FUNCTION  (TeamRepository.calculateTeamPoints):
SELECT calc_team_total_points(1);
