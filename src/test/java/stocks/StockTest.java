package stocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockTest {

    public String expectedSymbol;
    public String expectedName;
    public String expectedInfo;
    public Double expectedStockPrice;
    public Integer expectedNumShares;
    public Double expectedValue;
    public Stock test;

    @Before
    public void init(){
        expectedSymbol = "msft";
        expectedName = "Microsoft";
        expectedInfo = "Tech company";
        expectedStockPrice = 134.0;
        expectedNumShares = 10;
        expectedValue = 1340.0;

        test = new Stock(expectedSymbol, expectedName);
    }

    @Test
    public void constructorTest(){
        String actualSymbol = test.getSymbol();
        String actualName = test.getName();

        assertEquals(expectedSymbol, actualSymbol);
        assertEquals(expectedName, actualName);
    }

    @Test
    public void setGetInfo(){
        test.setInfo(expectedInfo);

        String actualInfo = test.getInfo();

        assertEquals(expectedInfo, actualInfo);
    }
}
