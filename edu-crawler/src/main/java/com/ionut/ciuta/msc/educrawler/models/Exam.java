package com.ionut.ciuta.msc.educrawler.models;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class Exam {
    public static final String RO_LANG = "LIMBA SI LITERATURA ROMANA";

    /**
     * Subject of the exam: Romanian Lit., Math etc.
     */
    private String subject;

    /**
     * Obtained results
     */
    private Grades grades;

    public Exam() {}

    public Exam(String subject, Grades grades) {
        this.subject = subject;
        this.grades = grades;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }
}
