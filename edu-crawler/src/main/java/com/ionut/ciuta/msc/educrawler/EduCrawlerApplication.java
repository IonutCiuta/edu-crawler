package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.tasks.CountyCrawlingTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EduCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduCrawlerApplication.class, args).close();

		Crawler crawler = new Crawler(4);
		crawler.crawl(new CountyCrawlingTask("AB", crawler));
	}
}
