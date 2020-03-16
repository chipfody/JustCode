package stocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    public Transaction test;
    public TransactionMeta metaData;
    public String expectedDate = "_2020_03";
    public Double expectedOpen = 165.3100;
    public Double expectedHigh = 175.0000;
    public Double expectedLow = 138.5000;
    public Double expectedClose = 145.7000;
    public Integer expectedVolume = 636200296;
    public Integer expectedNumOfShares = 3;

    @Before
    public void init(){
        metaData = TransactionMeta.TransactionMetaBuilder.newInstance()
                .setClose(expectedClose)
                .setDate(expectedDate)
                .setHigh(expectedHigh)
                .setLow(expectedLow)
                .setOpen(expectedOpen)
                .setVolume(expectedVolume)
                .build();
        test = new Transaction(metaData, expectedNumOfShares);
    }

    @Test
    public void constructorTest(){
        String actualDate = test.getDateOfTrade();
        Integer actualNumShares = test.getNumOfShare();
        Double actualCost = test.getCostPerShare();

        assertEquals(expectedDate, actualDate);
        assertEquals(expectedNumOfShares, actualNumShares);
        assertEquals(expectedClose, actualCost);
    }

    @Test
    public void calculateTransactionTest(){
        Double expectedTotal = expectedClose * expectedNumOfShares;

        Double actualTotal = test.calculateTransactionTotal();

        assertEquals(expectedTotal, actualTotal);
    }
}