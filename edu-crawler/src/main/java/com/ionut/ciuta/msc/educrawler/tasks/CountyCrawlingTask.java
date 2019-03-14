package com.ionut.ciuta.msc.educrawler.tasks;

import com.ionut.ciuta.msc.educrawler.Crawler;
import com.ionut.ciuta.msc.educrawler.cache.CacheService;
import com.ionut.ciuta.msc.educrawler.models.Unit;
import com.ionut.ciuta.msc.educrawler.parsers.UnitParser;
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
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class CountyCrawlingTask extends CrawlingTask {
    private static final Logger log = LoggerFactory.getLogger(CountyCrawlingTask.class);
    private final String county;
    private final Crawler crawler;
    private final StorageService storageService;
    private final CacheService cacheService;

    public CountyCrawlingTask(String county,
                              Crawler crawler,
                              StorageService storageService,
                              CacheService cacheService) {
        this.county = county;
        this.crawler = crawler;
        this.storageService = storageService;
        this.cacheService = cacheService;
    }

    @Override
    public void run() {
        log.info("Crawling units for {}", county);
        delay();

        Document docHtml = getUnitPage(1);
        List<Document> docs = new ArrayList<>();

        int pages = getNumberOfPages(docHtml);
        docs.add(docHtml);
        docs.addAll(getUnits(pages));

        List<List<Unit>> us = docs.stream().map(
                document -> {
                    List<Element> rows = getRows(document);
                    return rows.stream()
                            .map(row -> new UnitParser()
                                    .fromRow(row)
                                    .fromCounty(county)
                                    .getUnit()
                            ).collect(Collectors.toList());
                }
        ).collect(Collectors.toList());

        List<Unit> units = us.stream().flatMap(List::stream).collect(Collectors.toList());
        log.info("Units in county {}: {}", county, units.size());
        storageService.saveUnits(units);

        units.forEach(u -> crawler.crawl(
                new ResultCrawlingTask(
                        u.getId(),
                        county,
                        storageService,
                        cacheService
                )
        ));
    }

    private List<Element> getRows(Document document) {
        List<Element> rows = new ArrayList<>();
        rows.addAll(document.select(".tr1"));
        rows.addAll(document.select(".tr2"));
        return rows;
    }

    private List<Document> getUnits(int count) {
        List<Document> docs = new ArrayList<>();
        for(int i = 2; i <= count; i++) {
            docs.add(getUnitPage(i));
        }
        return docs;
    }

    private Document getUnitPage(Integer page) {
        String html;
        String fileName = cacheService.getCountyCacheFileName(county, page.toString());

        if(cacheService.isHtmlCached(fileName)) {
            html = cacheService.loadCachedHtml(fileName);
        } else {
            String url = Urls.build(county, page);
            log.info("Fetching unit page: {}", url);
            html = Http.get(url);
            cacheService.cacheHtml(fileName, html);
        }

        return Jsoup.parse(html);
    }
}
