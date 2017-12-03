package com.ionut.ciuta.msc.educrawler.parsers;

import com.ionut.ciuta.msc.educrawler.Text;
import com.ionut.ciuta.msc.educrawler.models.*;
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

    public StudentBuilder studiesAt(String unitId) {
        student.setUnit(unitId);
        return this;
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

        extractExams();
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

    private void extractExams() {
        student.addExam(ExamType.Ro_Lang, getRoLangExam());
        student.addExam(ExamType.Profile_1, getProfileExam1());
        student.addExam(ExamType.Profile_2, getProfileExam2());

        Exam mtLangExam = getMtLangExam();
        if(mtLangExam != null) {
            student.addExam(ExamType.Mt_Lang, mtLangExam);
        }
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

    private Exam getRoLangExam() {
        Grades grades = new Grades(
            Text.get(data.get(7)),
            Text.get(data.get(8)),
            Text.get(data.get(9))
        );

        return new Exam(Exam.RO_LANG, grades);
    }

    private Exam getMtLangExam() {
        String mtLang = Text.get(data.get(10));

        if(!mtLang.isEmpty()) {
            Grades grades = new Grades(
                Text.get(data.get(19)),
                Text.get(data.get(20)),
                Text.get(data.get(21))
            );

            return new Exam(mtLang, grades);
        }

        return null;
    }

    private Exam getProfileExam1() {
        String subject = Text.get(data.get(13));

        Grades grades = new Grades(
                Text.get(data.get(22)),
                Text.get(data.get(23)),
                Text.get(data.get(24))
        );

        return new Exam(subject, grades);
    }

    private Exam getProfileExam2() {
        String subject = Text.get(data.get(14));

        Grades grades = new Grades(
                Text.get(data.get(25)),
                Text.get(data.get(26)),
                Text.get(data.get(27))
        );

        return new Exam(subject, grades);
    }

    public Student getStudent() {
        return student;
    }
}
