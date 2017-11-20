package com.ionut.ciuta.msc.educrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * ionutciuta24@gmail.com on 20.11.2017.
 */
public class JsoupStudentTest {
    private final String html =
            "\n" +
                    "<SCRIPT>var noOfPages=4; var pageNo=1;</SCRIPT>\n" +
                    "<HTML>\n" +
                    "    <HEAD>\n" +
                    "        <META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
                    "        <TITLE>Bacalaureat 2010 - Rezultate</TITLE>\n" +
                    "        <LINK REL=Stylesheet TYPE=\"text/css\" HREF=\"../../../../../../css/all.css\">\n" +
                    "        <SCRIPT LANGUAGE=\"JavaScript\" SRC=\"../../../../../../scripts/f5.js\"></SCRIPT>\n" +
                    "    </HEAD>\n" +
                    "    <body topmargin=0 leftmargin=0 marginheight=\"0\" marginwidth=\"0\" scroll=yes bgcolor=white>\n" +
                    "        <TABLE WIDTH=\"100%\" HEIGHT=\"100%\" BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\">              \n" +
                    "            <table width=\"90%\" align=center cellspacing=0 class=mainTable id=mainTable onMouseOver=\"tblOnMouseOver()\" onMouseOut=\"tblOnMouseOut()\">                        \n" +
                    "                <tr class=\"tr2\" onClick=\"lock()\">\n" +
                    "                    <script>LuatDePeBacalaureatEduRo[\"DANILIUC N. \n" +
                    "                        <br>RALUCA\"]=\"DANILIUC N. \n" +
                    "                        <br>RALUCA\";LuatDePe_BacalaureatEduRo[\"DANILIUC N. \n" +
                    "                        <br>RALUCA\"]=\"9.7\";Luat_DePe_BacalaureatEduRo[\"DANILIUC N. \n" +
                    "                        <br>RALUCA\"]=\"REUSIT\";\n" +
                    "                    </script>\n" +
                    "                    <td class=td2 nowrap rowspan=\"2\" align=\"center\">&nbsp;10</td>\n" +
                    "                    <td class=td nowrap rowspan=\"2\" align=\"center\">&nbsp;\n" +
                    "                        <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(LuatDePeBacalaureatEduRo[\"DANILIUC N. \n" +
                    "                            <br>RALUCA\"]);\n" +
                    "                        </script>\n" +
                    "                    </td>\n" +
                    "                </td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;NU</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;ZI</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;STIINČ›E ALE NATURII</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\" &nbsp>&nbsp;Utilizator experimentat</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\" &nbsp>&nbsp;8.8</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;9.25</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;9.25</td>\n" +
                    "                <td class=td align=\"center\" colspan=\"4\">&nbsp; </td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;LIMBA ENGLEZA</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\" &nbsp>&nbsp;B1-B2-B2-B2-B2</td>\n" +
                    "                <td class=td align=\"center\" colspan=\"3\">&nbsp;MATEMATICA ST-NAT</td>\n" +
                    "                <td class=td align=\"center\" colspan=\"3\">&nbsp;BIOLOGIE VEGETALA Č™I ANIMALA</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;Utilizator experimentat</td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;\n" +
                    "                    <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(LuatDePe_BacalaureatEduRo[\"DANILIUC N. \n" +
                    "                        <br>RALUCA\"]);\n" +
                    "                    </script>\n" +
                    "                </td>\n" +
                    "                <td class=td rowspan=\"2\" align=\"center\">&nbsp;\n" +
                    "                    <script>if(window.location.href.indexOf(\"bacalaureat.edu.ro\")>=0) document.write(Luat_DePe_BacalaureatEduRo[\"DANILIUC N. \n" +
                    "                        <br>RALUCA\"]);\n" +
                    "                    </script>\n" +
                    "                </td>\n" +
                    "                </tr>\n" +
                    "                <tr class=\"tr2\" onClick=\"lock()\">\n" +
                    "                    <td class=td align=\"center\" &nbsp>&nbsp;&nbsp</td>\n" +
                    "                    <td class=td align=\"center\" &nbsp>&nbsp; </td>\n" +
                    "                    <td class=td align=\"center\">&nbsp; </td>\n" +
                    "                    <td class=td align=\"center\">&nbsp; </td>\n" +
                    "                    <td class=td align=\"center\" &nbsp>&nbsp;10</td>\n" +
                    "                    <td class=td align=\"center\">&nbsp; </td>\n" +
                    "                    <td class=td align=\"center\">&nbsp;10</td>\n" +
                    "                    <td class=td align=\"center\" &nbsp>&nbsp;9.75</td>\n" +
                    "                    <td class=td align=\"center\">&nbsp;9.85</td>\n" +
                    "                    <td class=td align=\"center\">&nbsp;9.85</td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </TD>\n" +
                    "    </TR>\n" +
                    "</TABLE>\n" +
                    "</BODY>\n" +
                    "</HTML>";

    @Test
    public void student() throws Exception {
        Document document = Jsoup.parse(html);
        List<Element> doubleRows = document.select(".tr2");
        assertEquals(2, doubleRows.size());
    }
}
