package com.ionut.ciuta.msc.educrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class JsoupStudentTest {
    private final String html =
                    "                <tr class=\"tr1\" onClick=\"lock()\">\n" +
                            "                    <script>LuatDePeBacalaureatEduRo[\"DEAK F\n" +
                            "        <br>HUBA-GABRIEL\"]=\"DEAK F \n" +
                            "        <br>HUBA-GABRIEL\";LuatDePe_BacalaureatEduRo[\"DEAK F \n" +
                            "        <br>HUBA-GABRIEL\"]=\"7.9\";Luat_DePe_BacalaureatEduRo[\"DEAK F \n" +
                            "        <br>HUBA-GABRIEL\"]=\"REUSIT\";\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "                    </script>\n" +
                            "                    <td class=td2 nowrap rowspan=\"2\" align=\"center\">&nbsp;9</td>\n" +
                            "                    <td class=td nowrap rowspan=\"2\" align=\"center\">&nbsp;\n" +
                            "                        <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(LuatDePeBacalaureatEduRo[\"DEAK F\n" +
                            "            <br>HUBA-GABRIEL\"]);\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "                        </script>\n" +
                            "                    </td>\n" +
                            "                    </td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;NU</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;ZI</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;MATEMATICA-INFORMATICA</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\" &nbsp>&nbsp;Utilizator experimentat</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\" &nbsp>&nbsp;6.75</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;6.75</td>\n" +
                            "                    <td class=td align=\"center\" colspan=\"4\">&nbsp;LIMBA MAGHIARA (REAL)</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;LIMBA ENGLEZA</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\" bgcolor=lightgreen>&nbsp;&nbsp</td>\n" +
                            "                    <td class=td align=\"center\" colspan=\"3\">&nbsp;MATEMATICA MATE-INFO</td>\n" +
                            "                    <td class=td align=\"center\" colspan=\"3\">&nbsp;ANATOMIE Č™I FIZIOLOGIE UMANA, GENETICA Č™I ECOLOGIE\n" +
                            "                        UMANA\n" +
                            "                    </td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;&nbsp</td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;\n" +
                            "                        <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(LuatDePe_BacalaureatEduRo[\"DEAK F\n" +
                            "        <br>HUBA-GABRIEL\"]);\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "                        </script>\n" +
                            "                    </td>\n" +
                            "                    <td class=td rowspan=\"2\" align=\"center\">&nbsp;\n" +
                            "                        <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(Luat_DePe_BacalaureatEduRo[\"DEAK F\n" +
                            "        <br>HUBA-GABRIEL\"]);\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "                        </script>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr class=\"tr1\" onClick=\"lock()\">\n" +
                            "                    <td class=td align=\"center\" &nbsp>&nbsp;Utilizator experimentat</td>\n" +
                            "                    <td class=td align=\"center\" &nbsp>&nbsp;7.15</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;7.15</td>\n" +
                            "                    <td class=td align=\"center\" &nbsp>&nbsp;9</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;9</td>\n" +
                            "                    <td class=td align=\"center\" &nbsp>&nbsp;8.7</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;</td>\n" +
                            "                    <td class=td align=\"center\">&nbsp;8.7</td>\n" +
                            "                </tr>";

    @Test
    public void parseStudentName() throws Exception {
        Document document = Jsoup.parse(html);
        String content = document.select("script").first().data();


        String cleanContent = content
                .toUpperCase()
                .replaceAll("\n|_|\"|;|<BR>", "")
                .replace("LUATDEPEBACALAUREATEDURO", "")
                .replaceAll("\\s+", " ")
                .replaceAll("\\[.*?\\]", " ")
                .trim();

        List<String> data = Arrays.stream(cleanContent.split("="))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
        System.out.println(data);

        assertEquals(3, data.size());
        assertEquals("DEAK F HUBA-GABRIEL", data.get(0));
        assertEquals("7.9", data.get(1));
        assertEquals("REUSIT", data.get(2));
    }
}
