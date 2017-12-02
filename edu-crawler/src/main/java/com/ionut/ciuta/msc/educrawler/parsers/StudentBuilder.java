package com.ionut.ciuta.msc.educrawler.parsers;

import com.ionut.ciuta.msc.educrawler.models.Student;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ionutciuta24@gmail.com on 02.12.2017.
 */
public class StudentBuilder {
    private final Student student;
    private final List<Element> data;

    public StudentBuilder() {
        this.student = new Student();
        this.data = new ArrayList<>();
    }

    public StudentBuilder fromLine(List<Element> line) {
        data.addAll(line);

        List<String> generalInfo = extractGeneralInfo();
        student.setName(generalInfo.get(0));
        student.setAvgGrade(Float.parseFloat(generalInfo.get(1)));
        return this;
    }

    private List<String> extractGeneralInfo() {
        String cleanContent = data.get(0)
                .data()
                .toUpperCase()
                .replaceAll("\n|_|\"|;|<BR>", "")
                .replace("LUATDEPEBACALAUREATEDURO", "")
                .replaceAll("\\s+", " ")
                .replaceAll("\\[.*?\\]", " ")
                .trim();

        return Arrays.stream(cleanContent.split("="))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public Student getStudent() {
        return student;
    }
}
