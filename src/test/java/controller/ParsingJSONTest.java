package controller;

import org.junit.Test;

public class ParsingJSONTest {

    @Test
    public void makingJSONFileTest() {

        ParsingJSON parsingJSON = new ParsingJSON();
        parsingJSON.makingJSONFile(ApiController.fetchApiQuery(ApiController.createApiQuery("AAPL")));

    }
}