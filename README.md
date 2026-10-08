# Sports Tournament Management - layered Spring Boot (Entity > Repository > Service > Controller)

Same structure as the agriculture project: Spring Data JPA entities, repositories holding the
JOIN / subquery / procedure call / function call, services, REST controllers, SecurityConfig.

## Run in Eclipse
1. Open `src/main/resources/application.properties` and set `spring.datasource.password=` to your MySQL root password.
2. File > Import > Maven > Existing Maven Projects > select this folder.
3. Right-click `SportsTournamentApplication.java` > Run As > Java Application.
4. Open http://localhost:8080
(Stop any other app on port 8080 first. JDK 17+ and MySQL running are required.)

The database is created automatically. Hibernate creates the 4 tables from the entity classes,
then `DatabaseSetup` creates the function, procedure, trigger and sample data.

## Docker
    docker compose down -v      (only needed once, to drop any older data volume)
    docker compose up --build
Open http://localhost:9090

## Where things are
| Item | File |
|---|---|
| Tables | `entity/Team, Player, Match, Score.java` |
| JOIN (matches) | `repository/MatchRepository.java` > getMatchDetails |
| JOIN (players) | `repository/PlayerRepository.java` > getPlayerDetails |
| Subquery | `repository/TeamRepository.java` > getTeamsAboveAverage |
| Function call | `repository/TeamRepository.java` > calculateTeamPoints |
| Procedure call | `repository/ScoreRepository.java` > addMatchScore |
| Procedure / function / trigger SQL | `src/main/resources/db/routines.sql` |
| Sample data | `src/main/resources/db/sample-data.sql` |
| Business rules | `service/*.java` |
| REST API | `controller/*.java` |
| Errors as JSON | `exception/ApiExceptionHandler.java` |
| Frontend | `src/main/resources/static/index.html` |

## API
GET/POST/DELETE /api/teams, GET /api/teams/above-average, GET /api/teams/{id}/points,
GET/POST/DELETE /api/players, GET/POST/DELETE /api/matches, GET/POST /api/scores
(POST /api/scores calls the stored procedure; the trigger then updates match status and team points.)
