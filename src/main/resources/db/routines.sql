DELIMITER $$

DROP TRIGGER IF EXISTS trg_update_points_after_score$$
DROP FUNCTION IF EXISTS calc_team_total_points$$
DROP PROCEDURE IF EXISTS add_match_score$$

-- FUNCTION: league points for a team (win = 3, draw = 1, loss = 0)1
CREATE FUNCTION calc_team_total_points(p_team_id INT) RETURNS INT
READS SQL DATA
BEGIN
  DECLARE total INT;
  SELECT COALESCE(SUM(CASE WHEN s.score > o.score THEN 3
                           WHEN s.score = o.score THEN 1
                           ELSE 0 END), 0)
    INTO total
    FROM scores s
    JOIN scores o ON o.match_id = s.match_id AND o.team_id <> s.team_id
   WHERE s.team_id = p_team_id;
  RETURN total;
END$$

-- PROCEDURE: add a score for one team in a match
CREATE PROCEDURE add_match_score(IN p_match_id INT, IN p_team_id INT, IN p_score INT)
BEGIN
  DECLARE valid INT;
  SELECT COUNT(*) INTO valid FROM matches
   WHERE match_id = p_match_id AND (team1_id = p_team_id OR team2_id = p_team_id);
  IF valid = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Team is not part of this match';
  END IF;
  IF p_score < 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Score cannot be negative';
  END IF;
  INSERT INTO scores (match_id, team_id, score) VALUES (p_match_id, p_team_id, p_score);
END$$

-- TRIGGER: when both teams have a score, complete the match and update team points
CREATE TRIGGER trg_update_points_after_score
AFTER INSERT ON scores
FOR EACH ROW
BEGIN
  DECLARE t1 INT;
  DECLARE t2 INT;
  IF (SELECT COUNT(*) FROM scores WHERE match_id = NEW.match_id) = 2 THEN
    SELECT team1_id, team2_id INTO t1, t2 FROM matches WHERE match_id = NEW.match_id;
    UPDATE matches SET status = 'COMPLETED' WHERE match_id = NEW.match_id;
    UPDATE teams SET points = calc_team_total_points(team_id) WHERE team_id IN (t1, t2);
  END IF;
END$$

DELIMITER ;
