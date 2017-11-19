package com.ionut.ciuta.msc.educrawler;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class County {
    private static final List<String> all;

    static {
        all = init();
    }

    private static List<String> init() {
        Object[] countiesObj = new Object[0];
        try {
            countiesObj = ((JSONArray) new JSONParser()
                    .parse(new FileReader("counties.json")))
                    .toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.stream(countiesObj)
                .map(c -> (String)c)
                .collect(Collectors.toList());
    }

    public static List<String> getAll() {
        return all;
    }
}
