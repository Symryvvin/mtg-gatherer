package ru.aizen.parser;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.aizen.Language;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MetaInfoParser {
    private static final String PAGE_NAME = "ctl00_ctl00_ctl00_MainContent_SubContent_SubContentHeader_subtitleDisplay";
    private static final String URL = "http://gatherer.wizards.com/Pages/Card/Languages.aspx";
    private final int multiverseID;

    private String pageName;

    public MetaInfoParser(int multiverseID) {
        this.multiverseID = multiverseID;
    }

    public List<MetaInfo> parse() throws URISyntaxException, IOException {
        var page = Jsoup.connect(buildURL(multiverseID)).timeout(20000).get();
        var pageNameElement = page.getElementById(PAGE_NAME);
        if (!isPageEmpty(pageNameElement)) {
            pageName = page.getElementById(PAGE_NAME).text();
            var items = page.getElementsByClass("cardItem");
            if (items.isEmpty()) {
                return parseOnlyEnglishCard(multiverseID);
            } else {
                return parseCardItems(items, multiverseID);
            }
        }
        return null;
    }

    private boolean isPageEmpty(Element name) {
        return name == null;
    }

    private String buildURL(int multiverseID) throws URISyntaxException {
        return new URIBuilder(URL)
                .addParameter("multiverseID", String.valueOf(multiverseID))
                .build().toString();
    }

    private List<MetaInfo> parseOnlyEnglishCard(int multiverseID) {
        return Collections.singletonList(
                new MetaInfo(multiverseID, multiverseID, pageName, Language.EN));
    }

    private List<MetaInfo> parseCardItems(Elements items, int multiverseID) {
        var result = items.stream()
                .map(i -> parseData(i.getElementsByTag("td")))
                .collect(Collectors.toList());
        int oracleID = result.stream()
                .filter(i -> i.getLanguage() == Language.EN)
                .findAny()
                .map(MetaInfo::getMultiverseID)
                .orElse(multiverseID);
        result.forEach(card -> card.setOracleID(oracleID));
        return result;
    }


    private MetaInfo parseData(Elements td) {
        Element link = td.get(0).selectFirst("a");
        return new MetaInfo(extractID(link),
                extractName(link),
                extractLanguage(td.get(1)));
    }

    private Integer extractID(Element element) {
        return Integer.parseInt(element.attr("href")
                .replace("Details.aspx?multiverseid=", ""));
    }

    private String extractName(Element element) {
        return element.text();
    }


    private Language extractLanguage(Element element) {
        return Language.from(element.text());
    }


}
