package com.ionut.ciuta.msc.educrawler.tasks;

import com.ionut.ciuta.msc.educrawler.cache.CacheService;
import com.ionut.ciuta.msc.educrawler.models.Student;
import com.ionut.ciuta.msc.educrawler.parsers.StudentParser;
import com.ionut.ciuta.msc.educrawler.storage.StorageService;
import com.ionut.ciuta.msc.educrawler.tools.Http;
import com.ionut.ciuta.msc.educrawler.tools.Urls;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ionutciuta24@gmail.com on 04.12.2017.
 */
public class ResultCrawlingTask extends CrawlingTask {
    private static final Logger log = LoggerFactory.getLogger(ResultCrawlingTask.class);
    private final String county;
    private final String unitId;
    private final StorageService storageService;
    private final CacheService cacheService;

    public ResultCrawlingTask(String unitId,
                              String county,
                              StorageService storageService,
                              CacheService cacheService) {
        this.county = county;
        this.unitId = unitId;
        this.storageService = storageService;
        this.cacheService = cacheService;
    }

    @Override
    public void run() {
        log.info("Crawling results for unit {}, {}", unitId, county);
        delay();

        Document docHtml = getResultPage(1);
        List<Document> docs = new ArrayList<>();

        int pages = getNumberOfPages(docHtml);
        docs.add(docHtml);
        docs.addAll(getResults(pages));

        List<List<Student>> ss = docs.stream()
                .map(document -> getStudentsFromRows(getRows(document)))
                .collect(Collectors.toList());

        List<Student> students = ss.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        log.info("Students in unit {}, county {}: {}", unitId, county, students.size());
        storageService.saveStudents(students);
    }

    private List<Element> getRows(Document document) {
        List<Element> rows = document.select(".tr1");
        rows.addAll(document.select(".tr2"));
        return rows;
    }

    private List<Element> getDoubleRowCells(Element row1, Element row2) {
        List<Element> cells = new ArrayList<>();
        cells.add(row1.select("script").first());
        cells.addAll(row1.select("td"));
        cells.addAll(row2.select("td"));
        return cells;
    }

    private List<Student> getStudentsFromRows(List<Element> rows) {
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < rows.size(); i+= 2) {
            List<Element> cells = getDoubleRowCells(
                    rows.get(i),
                    rows.get(i+1)
            );

            students.add(new StudentParser()
                    .studiesAt(unitId)
                    .fromLine(cells)
                    .getStudent()
            );
        }
        return students;
    }

    private List<Document> getResults(int count) {
        List<Document> docs = new ArrayList<>();
        for(int i = 2; i <= count; i++) {
            docs.add(getResultPage(i));
        }
        return docs;
    }

    private Document getResultPage(Integer page) {
        String html;
        String fileName = cacheService.getResultsCacheFileName(county, unitId, page.toString());

        if(cacheService.isHtmlCached(fileName)) {
            html = cacheService.loadCachedHtml(fileName);
        } else {
            String url = Urls.build(county, unitId, page);
            log.info("Fetching result page: {}", url);

            html = Http.get(url);
            cacheService.cacheHtml(fileName, html);
        }

        return Jsoup.parse(html);
    }
}
