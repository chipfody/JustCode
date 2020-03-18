package sql;

import stocks.Transaction;
import stocks.TransactionMeta;

import java.sql.*;

public class SqlController {
    private static Connection connection = null;
    private String symbol;

    public static void connectSqlServer(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://zipcode-group-project.cx9szw6knskg.us-east-1.rds.amazonaws.com:5432/stockprices",
                            "zcgroupproject", "cdhkvvkhdc");
            System.out.println("Opened database successfully");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database still open");

    }

    public static void insertStock(){
        //Connection connection = null;
        String symbol = "MSFT";
        //SqlController.createTable();
        String sql = "INSERT INTO AMZN (TICKER, MONTH, OPEN, HIGH, LOW, CLOSE, VOLUME) " +
                "VALUES ('MSFT', '_2020_03',165.31, 175.00, 138.58, 145.70, 636200296);";
        //Statement statement = null;
        try {
            //assert connection != null;
            Statement insert = connection.createStatement();

            insert.executeUpdate(sql);
            System.out.println(sql);
            insert.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static TransactionMeta getStock(String stockSymbol, String month){
        TransactionMeta.TransactionMetaBuilder builder = new TransactionMeta.TransactionMetaBuilder();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + stockSymbol +
                    " WHERE month = '" + month + "';";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                builder.setDate(rs.getString("month"))
                .setOpen(rs.getDouble("open"))
                .setHigh(rs.getDouble("high"))
                .setLow(rs.getDouble("low"))
                .setClose(rs.getDouble("close"))
                .setVolume(rs.getInt("volume"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return builder.build();
    }

    public static void createTable() {
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://zipcode-group-project.cx9szw6knskg.us-east-1.rds.amazonaws.com:5432/stockprices",
                            "zcgroupproject", "cdhkvvkhdc");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();
            String symbol = "AMZN";
            String sql = "CREATE TABLE " + symbol +
                    "(TICKER        VARCHAR(10) PRIMARY KEY NOT NULL," +
                    " MONTH         VARCHAR(15) NOT NULL," +
                    " OPEN          DECIMAL   NOT NULL, " +
                    " HIGH          DECIMAL   NOT NULL, " +
                    " LOW           DECIMAL   NOT NULL, " +
                    " CLOSE         DECIMAL  NOT NULL," +
                    " VOLUME        BIGINT)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
