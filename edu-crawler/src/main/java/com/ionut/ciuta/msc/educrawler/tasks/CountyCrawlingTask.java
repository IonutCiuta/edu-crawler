package com.ionut.ciuta.msc.educrawler.tasks;

import com.ionut.ciuta.msc.educrawler.Crawler;
import com.ionut.ciuta.msc.educrawler.Http;
import com.ionut.ciuta.msc.educrawler.Urls;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class CountyCrawlingTask extends CrawlingTask {
    private static final Logger log = LoggerFactory.getLogger(CountyCrawlingTask.class);
    private final String county;
    private final Crawler crawler;

    public CountyCrawlingTask(String county, Crawler crawler) {
        this.county = county;
        this.crawler = crawler;
    }

    @Override
    public void info() {
        log.info("Running task for {}", county);
    }

    @Override
    public void run() {
        Document docHtml = getUnitPage(null);
        List<Document> docs = new ArrayList<>();

        int pages = getNumberOfPages(docHtml);
        docs.add(docHtml);
        docs.addAll(getUnits(pages));

        System.out.println(docs.size());
    }

    private List<Document> getUnits(int count) {
        List<Document> docs = new ArrayList<>();
        for(int i = 2; i <= count; i++) {
            docs.add(getUnitPage(i));
        }
        return docs;
    }

    private Document getUnitPage(Integer page) {
        String html = Http.get(page == null ? Urls.build(county) : Urls.build(county, page));
        System.out.println(html);
        System.out.println();
        return Jsoup.parse(html);
    }

    public int getNumberOfPages(Document doc) {
        return Integer.parseInt(
                doc.select("script")
                .first()
                .data()
                .split(" ")[1]
                .split("=")[1]
                .split(";")[0]
        );
    }
}
