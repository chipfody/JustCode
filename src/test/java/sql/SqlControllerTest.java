package sql;

import org.junit.Test;
import stocks.TransactionMeta;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SqlControllerTest {

    @Test
    public void connectionTest(){
        SqlController.connectSqlServer();
    }

    @Test
    public void insertStockTest() throws SQLException {
        //SqlController.connectSqlServer();
        SqlController.insertStock("QVC", "_2020-03", 165.2134, 175.0056, 56.9832, 138.5819, 636200296);
    }

    @Test
    public void getStockTest(){
         TransactionMeta stock = SqlController.getStock("test", "_2020-03");
         Integer actual = stock.getVolume();
         Integer expected = 636200296;
         assertEquals(actual, expected);
    }

    @Test
    public void createTableTest() {
//        SqlController.connectSqlServer();
        SqlController.createTable("QVC");
 }

}