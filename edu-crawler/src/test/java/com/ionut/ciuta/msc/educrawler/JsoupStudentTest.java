package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.models.Student;
import com.ionut.ciuta.msc.educrawler.parsers.StudentBuilder;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class JsoupStudentTest {
    private Document document;

    @Before
    public void setUp() throws Exception {
        File file = new File("htmls/student.html");
        document = Jsoup.parse(file, "UTF-8");
    }

    @Ignore
    @Test
    public void parseStudentName() throws Exception {
        String content = document.select("script").first().data();

        StudentBuilder studentBuilder = new StudentBuilder();
        studentBuilder.fromLine(Collections.emptyList());
        Student student = studentBuilder.getStudent();


        assertEquals("DEAK F HUBA-GABRIEL", student.getName());
        assertTrue(7.9f == student.getAvgGrade());
    }
}

