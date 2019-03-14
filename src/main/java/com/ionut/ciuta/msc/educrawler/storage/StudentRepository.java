package com.ionut.ciuta.msc.educrawler.storage;

import com.ionut.ciuta.msc.educrawler.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ionutciuta24@gmail.com on 03.12.2017.
 */
@Repository
interface StudentRepository extends MongoRepository<Student, String> {
}
