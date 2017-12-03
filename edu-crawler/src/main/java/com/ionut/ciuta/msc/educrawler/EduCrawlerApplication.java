package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.cache.HtmlCacheService;
import com.ionut.ciuta.msc.educrawler.storage.StorageService;
import com.ionut.ciuta.msc.educrawler.storage.UnitRepository;
import com.ionut.ciuta.msc.educrawler.tasks.CountyCrawlingTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EduCrawlerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EduCrawlerApplication.class, args);
		StorageService storageService = context.getBean(StorageService.class);
		HtmlCacheService htmlCacheService = context.getBean(HtmlCacheService.class);

		Crawler crawler = new Crawler(4);
		Counties.getAll()
				.forEach(c -> crawler.crawl(new CountyCrawlingTask(c, crawler, storageService, htmlCacheService)));
		crawler.finish();
		//context.close();
	}
}
