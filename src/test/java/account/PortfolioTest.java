package account;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import stocks.Stock;
import stocks.Transaction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PortfolioTest {
    Portfolio portfolio;

    Logger logger = Logger.getLogger(PortfolioTest.class.getName());

    @Before
    public void setUP(){
        portfolio = new Portfolio();
    }


    @Test
    public void checkToSeeIfOwnStockTest(){
        Stock stock = new Stock("MSFT", "Microsoft");
        Transaction transaction = Transaction.makeTransaction("Test","_2020-03",10);
        stock.addTransaction(transaction);
        portfolio.addStockToPortfolio(stock);
        assertTrue(portfolio.checkToSeeIfOwnStock("MSFT"));
    }

    @Test
    public void getEquityOfStockTest(){

    }

    @Test
    public void purchaseStockTest(){
        assertFalse(portfolio.checkToSeeIfOwnStock("test"));
        portfolio.purchaseStock("Test","_2020-03", 10);
        assertTrue(portfolio.checkToSeeIfOwnStock("test"));
        portfolio.updateCurrentPortfolioValue("_2020-03");
        Double expectedBuyingPower = 1043.00;
        Double actualBuyingPower = portfolio.getBuyingPower();
        Double expectedPortfolioValue = 2500.00;
        Double actualPortfolioValue = portfolio.getCurrentPortfolioValue();
        assertEquals(expectedPortfolioValue,actualPortfolioValue);
        assertEquals(expectedBuyingPower,actualBuyingPower);
    }




}
