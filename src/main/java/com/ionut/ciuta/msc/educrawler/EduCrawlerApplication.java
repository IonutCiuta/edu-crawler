package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.cache.CacheService;
import com.ionut.ciuta.msc.educrawler.models.Counties;
import com.ionut.ciuta.msc.educrawler.storage.StorageService;
import com.ionut.ciuta.msc.educrawler.tasks.CountyCrawlingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EduCrawlerApplication implements CommandLineRunner {

	@Autowired
	private StorageService storageService;

	@Autowired
	private CacheService cacheService;

	public static void main(String[] args) {
		SpringApplication.run(EduCrawlerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Crawler crawler = new Crawler(4);
		Counties.getAll()
				.forEach(c -> crawler.crawl(new CountyCrawlingTask(c, crawler, storageService, cacheService)));
	}
}
