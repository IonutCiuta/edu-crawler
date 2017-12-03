package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.models.ExamType;
import com.ionut.ciuta.msc.educrawler.models.Student;
import com.ionut.ciuta.msc.educrawler.parsers.StudentParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class JsoupStudentBuilderTest {
    private Document document;
    private final String unit = "unitId";

    @Before
    public void setUp() throws Exception {
        File file = new File("htmls/student.html");
        document = Jsoup.parse(file, "UTF-8");
    }

    @Test
    public void parseStudentWithContestaionAndNoMtLang() throws Exception {
        List<Element> evenRows = document.select(".tr2");
        assertEquals(2, evenRows.size());

        /* Input for builder */
        List<Element> studentLine = new ArrayList<>();

        /* Parse name/avg grade script */
        studentLine.add(evenRows.get(0).select("script").first());

        /* Table data */
        studentLine.addAll(evenRows.get(0).select("td"));
        studentLine.addAll(evenRows.get(1).select("td"));

        assertEquals(28, studentLine.size());

        Student student = new StudentParser()
                .studiesAt(unit)
                .fromLine(studentLine)
                .getStudent();

        System.out.println(student);
        assertEquals("DANILIUC N. RALUCA", student.getName());
        assertTrue(9.7f == student.getAvgGrade());
        assertTrue(student.isFirstAttempt());
        assertEquals(3, student.getCompetencies().size());
        assertEquals(3, student.getExams().size());
        assertEquals("8.8", student.getExams().get(ExamType.Ro_Lang).getGrades().getInitial());
        assertEquals("9.25", student.getExams().get(ExamType.Ro_Lang).getGrades().getContestation());
    }

    @Test
    public void parseStudentWithMtLangExamAndCompetency() {
        List<Element> oddRows = document.select(".tr1");

        assertEquals(2, oddRows.size());

        /* Input for builder */
        List<Element> studentLine = new ArrayList<>();

        /* Parse name/avg grade script */
        studentLine.add(oddRows.get(0).select("script").first());

        /* Table data */
        studentLine.addAll(oddRows.get(0).select("td"));
        studentLine.addAll(oddRows.get(1).select("td"));

        assertEquals(28, studentLine.size());

        Student student = new StudentParser()
                .studiesAt(unit)
                .fromLine(studentLine)
                .getStudent();

        System.out.println(student);

        assertEquals("DEAK F HUBA-GABRIEL", student.getName());
        assertTrue(7.9f == student.getAvgGrade());
        assertTrue(student.isFirstAttempt());
        assertEquals(2, student.getCompetencies().size());
        assertEquals(4, student.getExams().size());
        assertEquals("7.15", student.getExams().get(ExamType.Mt_Lang).getGrades().getInitial());
        assertEquals("", student.getExams().get(ExamType.Mt_Lang).getGrades().getContestation());
        assertEquals("7.15", student.getExams().get(ExamType.Mt_Lang).getGrades().getFinalGrade());
        assertEquals("Utilizator experimentat", student.getCompetencies().get("LIMBA MAGHIARA (REAL)"));
    }
}

