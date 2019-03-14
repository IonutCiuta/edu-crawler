package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.tools.Urls;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public class UrlsTest {

    @Test
    public void shouldReturnCountyUrl() throws Exception {
        assertEquals(
                "http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/",
                Urls.build("AB")
        );
    }

    @Test
    public void shouldReturnCountyPageUrl() throws Exception {
        assertEquals(
                "http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/page_2",
                Urls.build("AB", 2)
        );
    }

    @Test
    public void shouldReturnUnitUrl() throws Exception {
        assertEquals(
                "http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/245/rezultate_finale/alfabetic/",
                Urls.build("AB", "245")
        );
    }

    @Test
    public void shouldReturnUnitPageUrl() throws Exception {
        assertEquals(
                "http://static.bacalaureat.edu.ro/2017/rapoarte/AB/lista_unitati/245/rezultate_finale/alfabetic/page_1",
                Urls.build("AB", "245", 1)
        );
    }
}