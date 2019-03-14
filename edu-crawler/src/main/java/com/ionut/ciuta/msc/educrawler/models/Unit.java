package com.ionut.ciuta.msc.educrawler.models;

import org.springframework.data.annotation.Id;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class Unit {
    @Id
    private String id;

    private String name;

    private String location;

    private String county;

    private String examinationCenter;

    public Unit() {
    }

    public Unit(String id, String name, String location, String county, String examinationCenter) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.county = county;
        this.examinationCenter = examinationCenter;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExaminationCenter() {
        return examinationCenter;
    }

    public void setExaminationCenter(String examinationCenter) {
        this.examinationCenter = examinationCenter;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Unit\n" +
                "\tid: " + id + "\n" +
                "\tname: " + name + "\n" +
                "\tlocation: " + location + "\n" +
                "\tcounty: " + county + "\n" +
                "\texaminationCenter: " + examinationCenter + "\n" +
                "\n";
    }
}
