package account;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import stocks.Stock;
import stocks.Transaction;
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
        Stock stock = new Stock("Test", "Microsoft");
        Transaction transaction = Transaction.makeTransaction("Test","_2020-03",10);
        stock.addTransaction(transaction);
        portfolio.addStockToPortfolio(stock);
        stock.updateCurrentStockPrice("Test", "_2020-03");
        Integer expectedNumOfShares = 10;
        Integer actualNumOfShares = stock.getTotalNumOfShares();
        Double expected = 1457.00;
        Double actual = portfolio.getEquityOfStock(portfolio.getStockFromPortfolio("Test"));
        assertEquals(expectedNumOfShares,actualNumOfShares);
        assertEquals(expected,actual);
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

    @Test
    public void getPositionOfStock(){
        Stock stock = new Stock("Test", "Microsoft");
        Transaction transaction = Transaction.makeTransaction("Test","_2020-03",10);
        stock.addTransaction(transaction);
        portfolio.addStockToPortfolio(stock);
        portfolio.updateCurrentPortfolioValue("_2020-03");
        String expected = "Name : Microsoft\nSymbol : Test\nNumber of Shares : 10\nCurrent Price : 145.7\nEquity : 1457.0";
        String actual = portfolio.getPositionOfStock("Test");
        assertEquals(expected,actual);
    }

    @Test
    public void getPositionOfAllStock(){
        Stock stock = new Stock("test", "Microsoft");
        Transaction transaction = Transaction.makeTransaction("test","_2020-03",10);
        stock.addTransaction(transaction);
        Stock stock2 = new Stock("test2", "Apple");
        Transaction transaction2 = Transaction.makeTransaction("test2","_2020-03",4);
        stock.addTransaction(transaction);
        stock2.addTransaction(transaction2);
        portfolio.addStockToPortfolio(stock);
        portfolio.addStockToPortfolio(stock2);
        portfolio.updateCurrentPortfolioValue("_2020-03");
        System.out.println(portfolio.getAllPositionsFromPortfolio());
    }

}
