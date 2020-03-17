package sql;

import org.junit.Test;

import static org.junit.Assert.*;

public class SqlControllerTest {

    @Test
    public void connectionTest(){
        SqlController.connectSqlServer();
    }

    @Test
    public void getStockTest(){
        SqlController.connectSqlServer();
        SqlController.getStock("msft", "_2020_03");
    }

    @Test
    public void createTableTest() {
        //SqlController.connectSqlServer();
        SqlController.createTable();
    }

}