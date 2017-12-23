package ru.shunt.wowstat.database;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Shunt on 20-Aug-16.
 */
public interface ParsingRepository extends CrudRepository<ParsingInfo, Long> {

    ParsingInfo findById(int id);
}
