package com.ionut.ciuta.msc.educrawler.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;
import java.util.Map;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class Student {
    /**
     * MongoDB generated ID
     */
    @Id
    private String id;

    /**
     * Full name containing the initials of the father
     */
    private String name;

    /**
     * Studies profile
     */
    private String profile;

    /**
     * Unit ID
     */
    private String unit;

    /**
     * First time attempting exam
     */
    private boolean firstAttempt;

    /**
     * Competencies tests and grades
     */
    private Map<String, String> competencies;

    /**
     * Exams and results
     */
    private Map<ExamType, Exam> exams;

    /**
     * Average grade
     */
    private float avgGrade;

    public Student() {
        this.competencies = new HashMap<>();
        this.exams = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isFirstAttempt() {
        return firstAttempt;
    }

    public void setFirstAttempt(boolean firstAttempt) {
        this.firstAttempt = firstAttempt;
    }

    public Map<String, String> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(Map<String, String> competencies) {
        this.competencies = competencies;
    }

    public Map<ExamType, Exam> getExams() {
        return exams;
    }

    public void setExams(Map<ExamType, Exam> exams) {
        this.exams = exams;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public void addCompetency(String name, String result) {
        if(name == null || name.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        competencies.put(name, result);
    }

    public void addExam(ExamType examType, Exam exam) {
        if(exam == null || examType == null) {
            throw new UnsupportedOperationException();
        }
        exams.put(examType, exam);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", unit='" + unit + '\'' +
                ", firstAttempt=" + firstAttempt +
                ", competencies=" + competencies +
                ", exams=" + exams +
                ", avgGrade=" + avgGrade +
                '}';
    }
}
