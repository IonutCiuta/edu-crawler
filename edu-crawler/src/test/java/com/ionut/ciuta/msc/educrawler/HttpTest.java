package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.tools.Http;
import com.ionut.ciuta.msc.educrawler.tools.Urls;
import org.junit.Test;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public class HttpTest {

    @Test
    public void getShouldPass() throws Exception {
        System.out.println(Http.get(Urls.build("AB")));
    }
}