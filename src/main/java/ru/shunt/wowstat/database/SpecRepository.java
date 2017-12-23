package ru.shunt.wowstat.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpecRepository extends CrudRepository<Specialization, Long> {

	Specialization findBySpecIdAndBracketName(long specId, String bracketName);
	
	@Query(value = "SELECT sum(s.playersAmount) FROM Specialization s WHERE s.bracketName=:bracket")
	long getTotalNumberOfPlayers(@Param("bracket") String bracketName);
}
