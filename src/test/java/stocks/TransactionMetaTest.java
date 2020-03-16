package stocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionMetaTest {

    public TransactionMeta test;
    public String expectedDate = "_2020_03";
    public Double expectedOpen = 165.3100;
    public Double expectedHigh = 175.0000;
    public Double expectedLow = 138.5000;
    public Double expectedClose = 145.7000;
    public Integer expectedVolume = 636200296;

    @Before
    public void init(){
        test = TransactionMeta.TransactionMetaBuilder.newInstance()
                .setClose(expectedClose)
                .setDate(expectedDate)
                .setHigh(expectedHigh)
                .setLow(expectedLow)
                .setOpen(expectedOpen)
                .setVolume(expectedVolume)
                .build();
    }

    @Test
    public void constructorTest() {
        String actualDate = test.getDate();
        Double actualOpen = test.getOpen();
        Double actualHigh = test.getHigh();
        Double acutalLow = test.getLow();
        Double actualClose = test.getClose();
        Integer actualVolume = test.getVolume();

        assertEquals(expectedClose, actualClose);
        assertEquals(expectedDate, actualDate);
        assertEquals(expectedHigh, actualHigh);
        assertEquals(expectedOpen, actualOpen);
        assertEquals(expectedVolume, actualVolume);
        assertEquals(expectedLow, acutalLow);
    }
}