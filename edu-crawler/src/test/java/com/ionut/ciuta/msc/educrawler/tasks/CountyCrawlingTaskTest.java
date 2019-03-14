package com.ionut.ciuta.msc.educrawler.tasks;

import org.jsoup.Jsoup;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class CountyCrawlingTaskTest {
    private String html = "<SCRIPT>var noOfPages=4; var pageNo=1;</SCRIPT><SCRIPT>var test=5</SCRIPT>";

    @Test
    public void getNumberOfPagesShouldPass() throws Exception {
        CountyCrawlingTask task = new CountyCrawlingTask("", null, null, null);
        assertEquals(4, task.getNumberOfPages(Jsoup.parse(html)));
    }

}