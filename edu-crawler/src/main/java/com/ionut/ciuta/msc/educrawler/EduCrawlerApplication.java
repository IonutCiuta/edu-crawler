package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.cache.CacheService;
import com.ionut.ciuta.msc.educrawler.storage.StorageService;
import com.ionut.ciuta.msc.educrawler.tasks.CountyCrawlingTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EduCrawlerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EduCrawlerApplication.class, args);
		StorageService storageService = context.getBean(StorageService.class);
		CacheService cacheService = context.getBean(CacheService.class);

		Crawler crawler = new Crawler(4);
		Counties.getAll()
				.forEach(c -> crawler.crawl(new CountyCrawlingTask(c, crawler, storageService, cacheService)));
		//crawler.finish();
		//context.close();
	}
}
