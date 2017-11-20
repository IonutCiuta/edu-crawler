package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.storage.UnitRepository;
import com.ionut.ciuta.msc.educrawler.tasks.CountyCrawlingTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EduCrawlerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EduCrawlerApplication.class, args);
		UnitRepository repository = context.getBean(UnitRepository.class);


		Crawler crawler = new Crawler(4);
		County.getAll().forEach(c -> crawler.crawl(new CountyCrawlingTask(c, crawler, repository)));
		crawler.finish();
		//context.close();
	}
}
