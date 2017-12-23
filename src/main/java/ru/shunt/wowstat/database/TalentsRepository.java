package ru.shunt.wowstat.database;

import org.springframework.data.repository.CrudRepository;

public interface TalentsRepository extends CrudRepository<Talents, Long> {

	Talents findByBracketAndSpecIdAndTalentId(String bracket, long specId, long talentId);
}
