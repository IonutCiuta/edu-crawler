package com.ionut.ciuta.msc.educrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import javax.print.Doc;
import java.util.regex.Pattern;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class JsoupTest {

    String html = "<SCRIPT>var noOfPages=4; var pageNo=1;</SCRIPT><SCRIPT>var test=5</SCRIPT>";

    @Test
    public void fetchTest() throws Exception {
        Document doc = Jsoup.parse(html);
        System.out.println(
                doc.select("script")
                        .first()
                        .data()
                        .split(" ")[1]
                        .split("=")[1]
                        .split(";")[0]);

    }
}
