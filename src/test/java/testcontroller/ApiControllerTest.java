package testcontroller;

import controller.ApiController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiControllerTest {

    @Test
    public void testCreateApiQuery(){
        String symbol = "msft";
        String actual =  ApiController.createApiQuery(symbol);
        String expected = "https://alpha-vantage.p.rapidapi.com/query?datatype=json&symbol=MSFT&function=TIME_SERIES_MONTHLY";
        assertEquals(expected,actual);
    }


    // IMPORTANT! This test makes calls to the API, change the symbol to retrieve different stock tickers!
    @Test
    public void testFetchApiQuery(){
        String symbol = "bp";
        String resultOfCall = ApiController.fetchApiQuery(ApiController.createApiQuery(symbol));
        System.out.println(resultOfCall);
    }
}
