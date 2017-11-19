package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.tasks.CrawlingTask;
import com.ionut.ciuta.msc.educrawler.tasks.ParsingTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public class Crawler {
    private int threadCount;
    private final ExecutorService executor;

    public Crawler(int threadCount) {
        this.threadCount = threadCount;
        this.executor = Executors.newFixedThreadPool(this.threadCount);
    }

    public void crawl(CrawlingTask task) {
        executor.execute(task);
    }

    public void parse(ParsingTask task) {
        executor.execute(task);
    }

    public void finish() {
        executor.shutdown();
    }
}
