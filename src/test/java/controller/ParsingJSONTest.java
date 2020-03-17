package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingJSONTest {

    @Test
    public void makeMonthObjectsTest() {
        ParsingJSON parsingJSON = new ParsingJSON();
        parsingJSON.makeMonthObjects(ApiController.fetchApiQuery(ApiController.createApiQuery("AAPL")));

        System.out.println(parsingJSON.getSymbol());
        System.out.println(parsingJSON.getDateOfMonth());
        System.out.println(parsingJSON.getOpen());
        System.out.println(parsingJSON.getHigh());
        System.out.println(parsingJSON.getLow());
        System.out.println(parsingJSON.getClose());
        System.out.println(parsingJSON.getVolume());

    }
}