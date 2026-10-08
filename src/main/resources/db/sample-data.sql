INSERT INTO teams (team_name, city, coach, points) VALUES
 ('Chennai Lions','Chennai','R. Kumar',0),
 ('Mumbai Tigers','Mumbai','S. Patil',0),
 ('Delhi Eagles','Delhi','A. Singh',0),
 ('Kolkata Knights','Kolkata','D. Sen',0);

INSERT INTO players (player_name, team_id, position, jersey_number) VALUES
 ('Arjun Rao',1,'Forward',9),('Vikram S',1,'Goalkeeper',1),
 ('Rohan Mehta',2,'Midfielder',8),('Sameer Joshi',2,'Defender',4),
 ('Karan Gill',3,'Forward',10),('Imran Khan',3,'Defender',5),
 ('Sourav Das',4,'Midfielder',7),('Anil Bose',4,'Goalkeeper',1);

INSERT INTO matches (team1_id, team2_id, match_date, venue, status) VALUES
 (1,2,'2026-09-10','Chepauk Stadium','SCHEDULED'),
 (3,4,'2026-09-12','Kotla Ground','SCHEDULED'),
 (1,3,'2026-09-15','Chepauk Stadium','SCHEDULED'),
 (2,4,'2026-09-20','Wankhede Stadium','SCHEDULED');

CALL add_match_score(1,1,3);
CALL add_match_score(1,2,1);
CALL add_match_score(2,3,2);
CALL add_match_score(2,4,2);
CALL add_match_score(3,1,0);
CALL add_match_score(3,3,1);
