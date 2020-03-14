package sql;

import java.sql.*;

public class SqlController {
    private static Connection connection = null;

    public static void connectSqlServer(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/stocks");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    public static void insertStock(){
        try {
            Statement insert = connection.createStatement();
            String sql = "INSERT INTO _2020_03 (TICKER, OPEN, HIGH, LOW, CLOSE, VOLUME) " +
                    "VALUES ('MSFT', 165.3100, 175.0000, 138.5800, 145.7000, 636200296);";
            insert.executeUpdate(sql);
            insert.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void getStock(String stock, String month){
        try{
            Statement insert = connection.createStatement();
            String sql = "SELECT * FROM " + month +
                    " WHERE TICKER = '" + stock.toUpperCase() + "';";
            ResultSet rs = insert.executeQuery(sql);
            while (rs.next()){
                Double open = rs.getDouble("open");
                Double high = rs.getDouble("high");
                Double low = rs.getDouble("low");
                Double close = rs.getDouble("close");
                Integer volume = rs.getInt("volume");
                System.out.println(new StringBuilder().append(open + " ").append(high + " ")
                        .append(low + " ").append(close + " ").append(volume));
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
