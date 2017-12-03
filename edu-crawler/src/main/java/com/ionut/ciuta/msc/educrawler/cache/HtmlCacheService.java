package com.ionut.ciuta.msc.educrawler.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * ionutciuta24@gmail.com on 03.12.2017.
 */
@Component
public class HtmlCacheService {
    Logger logger = LoggerFactory.getLogger(HtmlCacheService.class);

    public String getCountyCacheFileName(String county, String unitId, String page) {
        return "cache/units/".concat(getCacheFileName(county, unitId, page));
    }

    public String getResultsCacheFileName(String county, String unitId, String page) {
        return "cache/results/".concat(getCacheFileName(county, unitId, page));
    }

    private String getCacheFileName(String county, String unitId, String page) {
        return String.format("%s_%s_%s", county, unitId, page);
    }

    public boolean isHtmlCached(String fileName) {
        return new File(fileName).exists();
    }

    public void cacheHtml(String fileName, String content) {
        File file = new File(fileName);
        if(!file.exists()) {
            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"))) {
                writer.write(content);
            } catch (IOException e) {
                logger.error("Caching of {} failed {}", fileName, e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.warn("File {} already cached", fileName);
        }
    }

    public String loadCachedHtml(String fileName) {
        File file = new File(fileName);
        if(file.exists() && file.isFile()) {
            try {
                return new String(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                logger.error("File {} can't be read", fileName);
                throw new UnsupportedOperationException();
            }
        } else {
            logger.error("File {} is not cached!", fileName);
            throw new UnsupportedOperationException();
        }
    }

    public boolean removeCachedHtml(String fileName) {
        boolean deleted = new File(fileName).delete();

        logger.info("Delete file {}: {}", fileName, deleted ? "performed" : "not present");
        return deleted;
    }
}
