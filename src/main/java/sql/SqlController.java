package sql;

import stocks.Transaction;
import stocks.TransactionMeta;

import java.sql.*;

public class SqlController {
    private static Connection connection = null;

    public static void insertStock(String symbol, String month, Double open, Double high, Double low, Double close, String volume) throws SQLException {
        connectSqlServer();
        String  sql = "INSERT INTO " + symbol + " (ticker, month, open, high, low, close, volume) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);

            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, month);
            preparedStatement.setDouble(3, open);
            preparedStatement.setDouble(4, high);
            preparedStatement.setDouble(5, low);
            preparedStatement.setDouble(6, close);
            preparedStatement.setString(7, volume);

            preparedStatement.executeUpdate();
            preparedStatement.close();
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
        connectSqlServer();
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
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return builder.build();
    }

    public static void connectSqlServer(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://zipcode-group-project.cx9szw6knskg.us-east-1.rds.amazonaws.com:5432/stockprices",
                            "zcgroupproject", "cdhkvvkhdc");
            System.out.println("Opened database successfully in connect");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database remains open");
    }

    public static void createTable(String symbol) {
        connectSqlServer();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE " + symbol +
                    "(TICKER        VARCHAR(10) NOT NULL," +
                    " MONTH         VARCHAR(15) NOT NULL," +
                    " OPEN          DECIMAL   NOT NULL, " +
                    " HIGH          DECIMAL   NOT NULL, " +
                    " LOW           DECIMAL   NOT NULL, " +
                    " CLOSE         DECIMAL  NOT NULL," +
                    " VOLUME        VARCHAR(256) NOT NULL)";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
