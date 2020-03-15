package account;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import stocks.StockEnum;

public class PortfolioTest {
    Portfolio portfolio;

    @Before
    public void setUP(){
        portfolio = new Portfolio();
    }

    @Test
    public void addSingleStockTest(){
        assertTrue(portfolio.addStockToBasePortfolio(StockEnum.APPLE,4));
        assertEquals(365.20, portfolio.getCurrentPortfolioValue(), 0.00);
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
        Double expected = 709.20;
        Double actual = portfolio.getCurrentPortfolioValue();
        assertEquals(expected,actual);
        assertEquals(1790.80, portfolio.getBuyingPower(), 0.0);
    }

}
