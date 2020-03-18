package sql;

import org.junit.Test;
import stocks.TransactionMeta;

import static org.junit.Assert.*;

public class SqlControllerTest {

    @Test
    public void connectionTest(){
        SqlController.connectSqlServer();
    }

    @Test
    public void getStockTest(){
         TransactionMeta stock = SqlController.getStock("msft", "_2020-03");
         Integer actual = stock.getVolume();
         Integer expected = 636200296;
         assertEquals(actual, expected);
    }

    @Test
    public void createTableTest() {
        //SqlController.connectSqlServer();
        SqlController.createTable();
    }

}