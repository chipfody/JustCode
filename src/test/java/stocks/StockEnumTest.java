package stocks;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StockEnumTest {

    Logger logger = Logger.getLogger(StockEnumTest.class.getName());

    @Test
    public void classTest(){
        for (StockEnum stock : StockEnum.values()) {
            logger.log(Level.INFO,stock.toString() + " " + stock.getAllInfo());
            //For concise view
            //System.out.println(stock.toString() + " " + stock.getAllInfo());
        }
    }
}
