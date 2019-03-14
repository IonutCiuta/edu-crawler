package com.ionut.ciuta.msc.educrawler.cache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * ionutciuta24@gmail.com on 03.12.2017.
 */
public class CacheServiceTest {
    @InjectMocks
    private CacheService cacheService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountyCachedFileNameShouldReturnValidName() throws Exception {
        assertEquals("caching/units/AB_page.html",
                cacheService.getCountyCacheFileName("AB", "page"));
    }

    @Test
    public void getResultCachedFileNameShouldReturnValidName() throws Exception {
        assertEquals("caching/results/AB_1_page.html",
                cacheService.getResultsCacheFileName("AB", "1", "page"));
    }

    @Test
    public void writeFileShouldSucceed() throws Exception {
        String fileName = "file";
        cacheService.cacheHtml(fileName, "test");
        assertTrue(cacheService.isHtmlCached(fileName));
        assertEquals("test", cacheService.loadCachedHtml(fileName));
        assertTrue(cacheService.removeCachedHtml(fileName));
        assertFalse(cacheService.isHtmlCached(fileName));
    }
}