package account;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import stocks.StockEnum;
import stocks.StockEnumTest;

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
    public void addSingleStockTest(){
        assertTrue(portfolio.addStockToBasePortfolio(StockEnum.APPLE,4));
        assertEquals(2500.00, portfolio.getCurrentPortfolioValue(), 0.00);
        assertEquals(2134.80, portfolio.getBuyingPower(), 0.00);
    }

    @Test
    public void addStockFailTest(){
        assertFalse(portfolio.addStockToBasePortfolio(StockEnum.APPLE,900));
    }

    @Test
    public void addMultipleStocks(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,5);
        Double expected = 2500.00;
        Double actual = portfolio.getCurrentPortfolioValue();
        assertEquals(expected,actual);
        assertEquals(1790.80, portfolio.getBuyingPower(), 0.0);
    }

    @Test
    public void getEquityOfShareTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,5);
        Double expected = 153.35;
        Double actual = portfolio.getEquityOfShare("jpmorganchase");
        assertEquals(expected,actual);
    }

    @Test
    public void numberOfSharesTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,10);
        Integer expected = 10;
        Integer actual = portfolio.getNumberOfShares("jpmorganchase");
        assertEquals(expected,actual);
    }

    @Test
    public void currentPortfolioValueTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,10);
        Double expected = 2500.00;
        Double actual = portfolio.getCurrentPortfolioValue();
        assertEquals(expected,actual);
    }


    @Test
    public void portfolioDiversityOfShareTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,5);
        assertEquals(0.06, portfolio.getPortfolioDiversityOfShare("jpmorganchase"), 0.00);
        assertEquals(0.04, portfolio.getPortfolioDiversityOfShare("microsoft"), 0.00);
        assertEquals(0.18, portfolio.getPortfolioDiversityOfShare("Apple"), 0.00);
        assertEquals(0.72, portfolio.getPortfolioDiversityOfShare("cash"), 0.00);
        logger.log(Level.INFO, portfolio.getPositionOfShare("Apple"));
    }

    @Test
    public void diversityPercentageTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,5);
        assertEquals(18.0, portfolio.getDiversityPercentage("Apple"),0.00);
    }

    @Test
    public void getAllPositionsTest(){
        portfolio.addStockToBasePortfolio(StockEnum.MICROSOFT,5);
        portfolio.addStockToBasePortfolio(StockEnum.APPLE,5);
        portfolio.addStockToBasePortfolio(StockEnum.JPMORGANCHASE,5);
        logger.log(Level.INFO, portfolio.getAllPositions());
    }





}
