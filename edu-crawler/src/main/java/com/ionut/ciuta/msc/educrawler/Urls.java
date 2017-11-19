package com.ionut.ciuta.msc.educrawler;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public class Urls {
    public static final String EDU_HOME = "http://static.bacalaureat.edu.ro/2017/rapoarte/";

    /**
     * E.g.: http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/
     */
    public static final String EDU_COUNTY = EDU_HOME + "{county}/lista_unitati/";

    /**
     * E.g.: http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/245/rezultate_finale/alfabetic/
     */
    public static final String EDU_UNIT = EDU_HOME + "{county}/lista_unitati/" + "{unit}/rezultate_finale/alfabetic/";

    public static String build(String county) {
        return UriComponentsBuilder
                .fromHttpUrl(EDU_COUNTY)
                .buildAndExpand(new Object[]{county})
                .toUriString();
    }

    public static String build(String county, int page) {
        return UriComponentsBuilder
                .fromHttpUrl(EDU_COUNTY)
                .pathSegment("page_" + page)
                .buildAndExpand(new Object[]{county})
                .toUriString();
    }

    public static String build(String county, String unit) {
        return UriComponentsBuilder
                .fromHttpUrl(EDU_UNIT)
                .buildAndExpand(new Object[]{county, unit})
                .toUriString();
    }
}
