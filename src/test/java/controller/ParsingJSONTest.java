package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingJSONTest {

    @Test
    public void makeMonthObjectsTest() {
        ParsingJSON parsingJSON = new ParsingJSON();
        parsingJSON.makeMonthObjects(ApiController.fetchApiQuery(ApiController.createApiQuery("AAPL")));

    }
}