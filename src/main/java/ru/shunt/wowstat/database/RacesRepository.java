package ru.shunt.wowstat.database;

/**
 * Created by Shunt on 30-Sep-16.
 */
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RacesRepository extends CrudRepository<Races, Long> {

    Races findByRaceIdAndBracketName(long raceId, String bracketName);

    @Query(value = "SELECT sum(s.playersAmount) FROM Races s WHERE s.bracketName=:bracket")
    long getTotalNumberOfPlayers(@Param("bracket") String bracketName);
}
