package com.ionut.ciuta.msc.educrawler;

import com.ionut.ciuta.msc.educrawler.parsers.UnitBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import javax.print.Doc;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class JsoupUnitTest {
    @Test
    public void parseTableTest() throws Exception {
        Document document = Jsoup.parse(unitsPage);

        /* All rows */
        List<Element> rows = document.select(".tr1");
        rows.addAll(document.select(".tr2"));
        assertEquals(10, rows.size());

        /* All columns for row 0*/
        Element element = rows.get(0);
        List<Element> columns = element.select(".td");
        assertEquals(3, columns.size());

        System.out.println(new UnitBuilder().fromRow(element).fromCounty("AB").getUnit());

        //Localitate
        System.out.println(columns.get(1).text().replace("\u00a0",""));

        //Centru examinare
        System.out.println(columns.get(2).text().replace("\u00a0",""));

        //Unitate ID
        System.out.println(columns.get(0).select("a").first().attr("href").split("/")[0]);

        //Nume unitate
        System.out.println(columns.get(0).select("tr").first().select("td").first().text().replace("\u00a0",""));
    }

    String unitsPage = "\n" +
            "<SCRIPT>var noOfPages=4; var pageNo=2;</SCRIPT>\n" +
            "<HTML>\n" +
            "    <HEAD>\n" +
            "        <META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
            "        <TITLE>Bacalaureat 2010</TITLE>\n" +
            "        <LINK REL=Stylesheet TYPE=\"text/css\" HREF=\"../../../css/all.css\">\n" +
            "        <SCRIPT LANGUAGE=\"JavaScript\" SRC=\"../../../scripts/f.js\"></SCRIPT>\n" +
            "    </HEAD>\n" +
            "    <body topmargin=0 leftmargin=0 marginheight=\"0\" marginwidth=\"0\" bgcolor=white>\n" +
            "        <TABLE WIDTH=100% HEIGHT=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>\n" +
            "            <TR>\n" +
            "                <TD HEIGHT=100% VALIGN=TOP>\n" +
            "                    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
            "                        <tr>\n" +
            "                            <td>&nbsp;</td>\n" +
            "                            <td>\n" +
            "                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                                    <tr>\n" +
            "                                        <td>&nbsp;</td>\n" +
            "                                        <td nowrap>\n" +
            "                                            <a href=\"http://portal.edu.ro/\" target=\"_top\" >Portal SEI</a>\n" +
            "                                        </td>\n" +
            "                                        <td nowrap>&nbsp;&nbsp;>>&nbsp;&nbsp;</td>\n" +
            "                                        <td nowrap>\n" +
            "                                            <a href=\"../../../index.html\" target=\"_top\">Bacalaureat 2010</a>\n" +
            "                                        </td>\n" +
            "                                        <td nowrap>&nbsp;&nbsp;>>&nbsp;&nbsp;</td>\n" +
            "                                        <td>&nbsp;</td>\n" +
            "                                        <td nowrap>\n" +
            "                                            <a href=\"../../index.html\" target=\"_top\">Rapoarte</a>\n" +
            "                                        </td>\n" +
            "                                        <td nowrap>&nbsp;&nbsp;>>&nbsp;&nbsp;</td>\n" +
            "                                        <td nowrap>\n" +
            "                                            <a href=\"../index.html\">Lista rapoartelor pentru ALBA</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td nowrap>&nbsp;&nbsp;>>&nbsp;&nbsp;</td><td nowrap>Lista unit&#259;&#355;ilor de &#238;nv&#259;&#355;&#259;m&#226;nt din jude&#355;ul AB</td>-->\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td align=\"right\" nowrap width=\"100%\">Data ultimei actualiz&#259;ri: 01.09.2017&nbsp;15:42&nbsp;</td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                    <br>\n" +
            "                    <center>\n" +
            "                        <font class=heading2>Lista liceelor din ALBA</font>\n" +
            "                    </center>\n" +
            "                    <br>\n" +
            "                    <table border=0>\n" +
            "                        <tr>\n" +
            "                            <td>\n" +
            "                                <script>drawFirstButton();</script>\n" +
            "                            </td>\n" +
            "                            <td>\n" +
            "                                <script>drawPrevButton();</script>\n" +
            "                            </td>\n" +
            "                            <td class=tx>\n" +
            "                                <SELECT NAME=pageNavigator ID=PageNavigator class=\"tx\" onChange=\"goToPage(this)\">\n" +
            "                                    <OPTION VALUE=1 class=\"opte\">pag. 1 (COLEGIUL DE AFACERI ALBA IULIA)</OPTION>\n" +
            "                                    <OPTION VALUE=2 SELECTED>pag. 2 (COLEGIUL TEHNIC \"ALEXANDRU DOMSA\" ALBA IULIA)</OPTION>\n" +
            "                                    <OPTION VALUE=3>pag. 3 (LICEUL DE ARTE \"REGINA MARIA\" ALBA IULIA)</OPTION>\n" +
            "                                    <OPTION VALUE=4 class=\"opte\">pag. 4 (LICEUL TEOLOGIC GRECO-CATOLIC \"SFANTUL VASILE CEL MARE\" BLAJ)</OPTION>\n" +
            "                                </SELECT>\n" +
            "                            </td>\n" +
            "                            <td>\n" +
            "                                <script>drawNextButton();</script>\n" +
            "                            </td>\n" +
            "                            <td>\n" +
            "                                <script>drawLastButton();</script>\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                    <table width=90% align=center cellspacing=0 class=mainTable id=mainTable onMouseOver=\"tblOnMouseOver()\" onMouseOut=\"tblOnMouseOut()\">\n" +
            "                        <tr>\n" +
            "                            <th class=th align=\"left\" nowrap>Nr. crt.</th>\n" +
            "                            <th class=th align=\"left\" nowrap title=\"Raportul este sortat dup&#259; aceast&#259; coloan&#259;\">Nume Unitate\n" +
            "                                <img src=\"../../../images/asc.gif\" border=0>\n" +
            "                            </th>\n" +
            "                            <th class=th align=\"left\" nowrap>Localitate</th>\n" +
            "                            <th class=th align=\"left\" nowrap>Nume centru examinare</th>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr1\">\n" +
            "                            <td class=td2 nowrap>&nbsp;11</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;COLEGIUL TEHNIC \"ALEXANDRU DOMSA\" ALBA IULIA </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1354/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1354/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"1354/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1354/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1354/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ALBA IULIA</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC \"APULUM\" ALBA IULIA</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr2\">\n" +
            "                            <td class=td2 nowrap>&nbsp;12</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;COLEGIUL TEHNIC \"APULUM\" ALBA IULIA </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1269/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1269/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"1269/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1269/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1269/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ALBA IULIA</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC \"APULUM\" ALBA IULIA</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr1\">\n" +
            "                            <td class=td2 nowrap>&nbsp;13</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;COLEGIUL TEHNIC \"DORIN PAVEL\" ALBA IULIA </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"858/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"858/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"858/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"858/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"858/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ALBA IULIA</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC \"DORIN PAVEL\" ALBA IULIA</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr2\">\n" +
            "                            <td class=td2 nowrap>&nbsp;14</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;COLEGIUL TEHNIC \"I. D. LAZARESCU\" CUGIR </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"282/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"282/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"282/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"282/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"282/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;CUGIR</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL NATIONAL \"LUCIAN BLAGA\" SEBES</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr1\">\n" +
            "                            <td class=td2 nowrap>&nbsp;15</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;COLEGIUL TEHNIC AIUD </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"667/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"667/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"667/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"667/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"667/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;AIUD</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC AIUD</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr2\">\n" +
            "                            <td class=td2 nowrap>&nbsp;16</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;LICEUL \"CORNELIU MEDREA\" ZLATNA </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1023/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1023/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"1023/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1023/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1023/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ZLATNA</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC \"APULUM\" ALBA IULIA</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr1\">\n" +
            "                            <td class=td2 nowrap>&nbsp;17</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;LICEUL \"DR. LAZAR CHIRILA\" BAIA DE ARIES </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"70/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"70/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"70/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"70/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"70/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;BAIA DE ARIES</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL NATIONAL \"AVRAM IANCU\" CIMPENI</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr2\">\n" +
            "                            <td class=td2 nowrap>&nbsp;18</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;LICEUL \"HOREA, CLOSCA SI CRISAN\" ABRUD </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1325/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1325/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"1325/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1325/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"1325/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ABRUD</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL NATIONAL \"AVRAM IANCU\" CIMPENI</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr1\">\n" +
            "                            <td class=td2 nowrap>&nbsp;19</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;LICEUL CU PROGRAM SPORTIV \" FLORIN FLESERIU\" SEBES </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"656/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"656/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"656/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"656/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"656/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;SEBES</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL NATIONAL \"LUCIAN BLAGA\" SEBES</td>\n" +
            "                        </tr>\n" +
            "                        <tr class=\"tr2\">\n" +
            "                            <td class=td2 nowrap>&nbsp;20</td>\n" +
            "                            <td class=td nowrap>\n" +
            "                                <table width =\"100%\">\n" +
            "                                    <tr>\n" +
            "                                        <td colspan = \"4\"> &nbsp;LICEUL CU PROGRAM SPORTIV ALBA IULIA </td>\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"959/lista_probe/index.html\">List&#259; probe</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"959/rezultate_partiale/index.html\">Rezultate par&#355;iale</a>\n" +
            "                                        </td>\n" +
            "                                        <!--<td width=\"70%\"><a href = \"959/rezultate_finale/alfabetic/index.html\">Lista candida&#355;ilor - alfabetic</a></td>-->\n" +
            "                                        <td>\n" +
            "                                            <a href = \"959/rezultate_finale/alfabetic/index.html\">Rezultate finale (alfabetic)</a>\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <a href = \"959/rezultate_finale/dupa_medie/index.html\">Rezultate finale (dup&#259; medie)</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                </table>\n" +
            "                            </td>\n" +
            "                            <td class=td nowrap>&nbsp;ALBA IULIA</td>\n" +
            "                            <td class=td nowrap>&nbsp;COLEGIUL TEHNIC \"APULUM\" ALBA IULIA</td>\n" +
            "                        </tr>\n" +
            "                    </table>\n" +
            "                </TD>\n" +
            "            </TR>\n" +
            "            <TR>\n" +
            "                <TD width=\"100%\" align=\"center\">\n" +
            "                    <iframe width=100% height=44 marginheight=0 marginwidth=0 frameborder=0 src=\"../../commonFooter.html\"> </iframe>\n" +
            "                </TD>\n" +
            "            </TR>\n" +
            "        </TABLE>\n" +
            "    </body>\n" +
            "</html>";
}
