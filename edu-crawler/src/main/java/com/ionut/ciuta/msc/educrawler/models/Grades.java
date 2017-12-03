package com.ionut.ciuta.msc.educrawler.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class Grades {
    @JsonProperty("i")
    private String initial;

    @JsonProperty("c")
    private String contestation;

    @JsonProperty("f")
    private String finalGrade;

    public String getInitial() {
        return initial;
    }

    public Grades() {
    }

    public Grades(String initial, String contestation, String finalGrade) {
        this.initial = initial;
        this.contestation = contestation;
        this.finalGrade = finalGrade;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getContestation() {
        return contestation;
    }

    public void setContestation(String contestation) {
        this.contestation = contestation;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "initial='" + initial + '\'' +
                ", contestation='" + contestation + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                '}';
    }
}
