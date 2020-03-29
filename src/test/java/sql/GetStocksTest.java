package sql;

import static org.junit.Assert.*;
import org.junit.Test;
import utilities.Messages;

import java.util.Map;

public class GetStocksTest {

    @Test
    public void getStartingStockTest(){
        Map<String,Double> actual = GetStocks.getAllStocksPrices("2008-12-31",GetStocks.startingStocks);
        assertEquals(actual.size(),20);
        assertEquals(85.35,actual.get("AAPL"),.00);
        assertEquals(56.06, actual.get("WMT"), .00);
    }

    @Test
    public void printStockPrices(){
        Messages.printStockPrices("2008-12-31",GetStocks.startingStocks);
    }

}
