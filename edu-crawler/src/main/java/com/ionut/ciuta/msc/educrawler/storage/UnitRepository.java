package com.ionut.ciuta.msc.educrawler.storage;

import com.ionut.ciuta.msc.educrawler.models.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ionutciuta24@gmail.com on 21.11.2017.
 */
@Repository
public interface UnitRepository extends MongoRepository<Unit, String> {
}
