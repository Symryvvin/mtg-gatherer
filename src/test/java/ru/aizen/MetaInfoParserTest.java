package ru.aizen;

import org.junit.Assert;
import org.junit.Test;
import ru.aizen.parser.MetaInfo;
import ru.aizen.parser.MetaInfoParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class MetaInfoParserTest {

    @Test
    public void parse447292() throws IOException, URISyntaxException {
        var parser = new MetaInfoParser(447292);
        var data = parser.parse();
        var expected = Arrays.asList("Schock", "電震", "Choc", "충격", "Choque",
                "Шок", "Shock", "ショック", "电震", "Choque");
        int oracleID = 447292;
        var actual = data.stream().map(MetaInfo::getName).collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(data.stream().allMatch(card -> card.getOracleID() == oracleID));
    }

    @Test
    public void parse459402() throws IOException, URISyntaxException {
        var parser = new MetaInfoParser(459402);
        var data = parser.parse();
        var expected = Arrays.asList("歐佐夫僭位卡婭", "Kaya, Orzhov-Thronräuberin", "Kaya, Orzhov Usurper",
                "Kaya, usurpatrice d'Orzhov", "Kaya, Usurpatrice Orzhov", "オルゾフの簒奪者、ケイヤ",
                "오르조브 찬탈자, 카야", "Kaya, Usurpadora Ozhov", "欧佐夫僭位卡娅", "Kaya, usurpadora orzhov");
        int oracleID = 457330;
        var actual = data.stream().map(MetaInfo::getName).collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(data.stream().allMatch(card -> card.getOracleID() == oracleID));
    }


    @Test
    public void parse1() throws IOException, URISyntaxException {
        var parser = new MetaInfoParser(1);
        var data = parser.parse();
        var expected = Collections.singletonList("Ankh of Mishra");
        int oracleID = 1;
        var actual = data.stream().map(MetaInfo::getName).collect(Collectors.toList());
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(data.stream().allMatch(card -> card.getOracleID() == oracleID));
    }

    @Test
    public void parseNull() throws IOException, URISyntaxException {
        var parser = new MetaInfoParser(500000);
        Assert.assertNull(parser.parse());
    }
}
