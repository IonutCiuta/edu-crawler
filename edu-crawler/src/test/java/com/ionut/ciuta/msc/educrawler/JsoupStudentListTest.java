package com.ionut.ciuta.msc.educrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ionutciuta24@gmail.com on 01.12.2017.
 */
public class JsoupStudentListTest {
    @Test
    public void parseStudentInfo() throws Exception {
        File file = new File("students.html");
        Document document = Jsoup.parse(file, "UTF-8");
        List<Element> oddRows = document.select(".tr1");
        List<Element> evenRows = document.select(".tr2");

        assertEquals(10, oddRows.size());
        assertEquals(10, evenRows.size());

        List<Element> line1 = oddRows.get(0).select("td");
        List<Element> line2 = oddRows.get(1).select("td");

        assertTrue(!line1.isEmpty());
    }
}
