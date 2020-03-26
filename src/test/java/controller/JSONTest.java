package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class JSONTest {

    JSON test;

    @Before
    public void init(){
        test = new JSON();
    }

    @Test
    public void makingJSONFileTest() throws IOException {
        test.parsingJSON(ApiController.fetchApiQuery(ApiController.createApiQuery("BP")));
    }

    @Test
    public void populateTest(){
        String[] symb = new String[]{"rost", "hrb"};
        test.populateDB();
    }
}