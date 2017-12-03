package com.ionut.ciuta.msc.educrawler.storage;

import com.ionut.ciuta.msc.educrawler.models.Student;
import com.ionut.ciuta.msc.educrawler.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 03.12.2017.
 */
@Service
public class StorageService {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void saveUnits(List<Unit> units) {
        unitRepository.save(units);
    }

    public void saveStuents(List<Student> students) {
        studentRepository.save(students);
    }
}
