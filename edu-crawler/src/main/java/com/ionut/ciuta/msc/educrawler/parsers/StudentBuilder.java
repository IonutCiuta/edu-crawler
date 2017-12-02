package com.ionut.ciuta.msc.educrawler.parsers;

import com.ionut.ciuta.msc.educrawler.Text;
import com.ionut.ciuta.msc.educrawler.models.Competency;
import com.ionut.ciuta.msc.educrawler.models.Student;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        student.setFirstAttempt(isFirstAttempt());
        student.setProfile(getProfile());
        extractCompetencies().stream()
                .filter(Objects::nonNull)
                .forEach(c -> student.addCompetency(c.getKey(), c.getValue()));
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

    private List<Competency> extractCompetencies() {
        return Arrays.asList(
                getRoLangComp(),
                getMtLangComp(),
                getMdLangComp(),
                getDigitalComp()
        );
    }

    private boolean isFirstAttempt() {
        return "NU".equals(Text.get(data.get(3)));
    }

    private String getProfile() {
        return Text.get(data.get(5));
    }

    private Competency getRoLangComp() {
        return Competency.getForRoLang(Text.get(data.get(6)));
    }

    private Competency getMtLangComp() {
        String mtLang = getMtLang();
        return !mtLang.isEmpty() ? new Competency(mtLang, getMtLangCompResult()) : null;
    }

    private String getMtLang() {
        return Text.get(data.get(10));
    }

    private String getMtLangCompResult() {
        return Text.get(data.get(18));
    }

    private Competency getMdLangComp() {
        String mdLangResult = getMdLangCompResult();
        return !mdLangResult.isEmpty() ? new Competency(getMdLang(), mdLangResult) : null;
    }

    private String getMdLang() {
        return Text.get(data.get(11));
    }

    private String getMdLangCompResult() {
        return Text.get(data.get(12));
    }

    private Competency getDigitalComp() {
        String digitalCompResult = getDigitalCompResult();
        return !digitalCompResult.isEmpty() ? Competency.getForDigital(digitalCompResult) : null;
    }

    private String getDigitalCompResult() {
        return Text.get(data.get(15));
    }

    public Student getStudent() {
        return student;
    }
}
