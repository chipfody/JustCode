package stocks;

import org.junit.Before;

import static org.junit.Assert.*;

public class TransactionTest {

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

    
}