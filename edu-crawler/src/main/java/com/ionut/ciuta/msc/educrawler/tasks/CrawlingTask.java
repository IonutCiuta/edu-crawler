package com.ionut.ciuta.msc.educrawler.tasks;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public abstract class CrawlingTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CrawlingTask.class);
    private static final long DELAY = 500;

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

    public void delay() {
        try {
            log.warn("Delaying task...");
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
