package com.ionut.ciuta.msc.educrawler.cache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * ionutciuta24@gmail.com on 03.12.2017.
 */
public class HtmlCacheServiceTest {
    @InjectMocks
    private HtmlCacheService htmlCacheService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountyCachedFileNameShouldReturnValidName() throws Exception {
        assertEquals("caching/units/AB_page.html",
                htmlCacheService.getCountyCacheFileName("AB", "page"));
    }

    @Test
    public void getResultCachedFileNameShouldReturnValidName() throws Exception {
        assertEquals("caching/results/AB_1_page.html",
                htmlCacheService.getResultsCacheFileName("AB", "1", "page"));
    }

    @Test
    public void writeFileShouldSucceed() throws Exception {
        String fileName = "file";
        htmlCacheService.cacheHtml(fileName, "test");
        assertTrue(htmlCacheService.isHtmlCached(fileName));
        assertEquals("test", htmlCacheService.loadCachedHtml(fileName));
        assertTrue(htmlCacheService.removeCachedHtml(fileName));
        assertFalse(htmlCacheService.isHtmlCached(fileName));
    }
}