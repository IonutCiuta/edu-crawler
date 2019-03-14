package com.ionut.ciuta.msc.educrawler.parsers;

import com.ionut.ciuta.msc.educrawler.models.Unit;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class UnitParser {
    private final Unit unit;
    private final List<Element> columns;

    public UnitParser() {
        this.unit = new Unit();
        this.columns = new ArrayList<>();
    }

    public UnitParser fromRow(Element row) {
        columns.addAll(row.select(".td"));
        unit.setId(findId());
        unit.setName(findName());
        unit.setLocation(findLocation());
        unit.setExaminationCenter(findExaminationLocation());
        return this;
    }

    public UnitParser fromCounty(String county) {
        unit.setCounty(county);
        return this;
    }

    public Unit getUnit() {
        return unit;
    }

    private String findId() {
        return columns.get(0)
                .select("a").first()
                .attr("href")
                .split("/")[0];
    }

    private String findName() {
        return columns.get(0)
                .select("tr").first()
                .select("td").first()
                .text().replace("\u00a0","");
    }

    private String findLocation() {
        return columns.get(1)
                .text().replace("\u00a0","");
    }

    private String findExaminationLocation() {
        return columns.get(2)
                .text().replace("\u00a0","");
    }
}
