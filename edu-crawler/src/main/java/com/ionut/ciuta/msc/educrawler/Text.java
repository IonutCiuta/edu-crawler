package com.ionut.ciuta.msc.educrawler;


import org.jsoup.nodes.Element;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class Text {
    private static final String nbsp = "\u00a0";

    private Text() {}

    public static String get(Element element) {
        return trim(element.text());
    }

    public static String trim(String text) {
        return text.replaceAll(nbsp, "");
    }

    public static String normalize(String text) {
        return text.replaceAll("\\.", "");
    }
}
