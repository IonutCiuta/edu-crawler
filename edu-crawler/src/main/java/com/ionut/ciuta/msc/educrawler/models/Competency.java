package com.ionut.ciuta.msc.educrawler.models;

import java.util.Map;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class Competency implements Map.Entry<String, String> {
    private static final String roLang = "LIMBA ROMANA";
    private static final String digital = "COMP. DIGITALE";

    private String name;
    private String result;

    public Competency(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public static Competency getForRoLang(String result) {
        return new Competency(roLang, result);
    }

    public static Competency getForDigital(String result) {
        return new Competency(digital, result);
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String getValue() {
        return result;
    }

    @Override
    public String setValue(String value) {
        this.result = value;
        return value;
    }
}
