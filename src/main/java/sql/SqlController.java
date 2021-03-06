package sql;

import java.sql.DriverManager;

import com.sun.istack.internal.logging.Logger;
import org.postgresql.Driver;
import account.User;
import stocks.Transaction;
import stocks.TransactionMeta;

import java.sql.*;
import java.util.logging.Level;
import java.time.LocalDate;

public class SqlController {
    private static Connection connection = null;
    static Logger logger = Logger.getLogger(SqlController.class);

    public static void insertStock(String symbol, String month, Double open, Double high, Double low, Double close, String volume) {
        SqlController.createTable(symbol);
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
                .setVolume(rs.getString("volume"));
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

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            logger.log(Level.INFO,"Connected!");
            return DriverManager.getConnection("jdbc:postgresql://zipcode-group-project.cx9szw6knskg.us-east-1.rds.amazonaws.com:5432/stockprices",
                    "zcgroupproject", "cdhkvvkhdc");
        } catch (SQLException e) {
            throw new RuntimeException("Can't Connect", e);
        }
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

    public static void createUserTable() {
        connectSqlServer();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE users" +
                    "(ID         INT PRIMARY KEY NOT NULL," +
                    " FIRSTNAME  VARCHAR(15) NOT NULL," +
                    " lASTNAME   VARCHAR(15)   NOT NULL, " +
                    " DOB        DATE   NOT NULL)";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("User table created successfully");
    }

    public static void insertUser(int id, String firstName, String lastName, LocalDate dob) throws SQLException {
        connectSqlServer();
        String  sql = "INSERT INTO users (ID, FIRSTNAME, LASTNAME, DOB) " +
                "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDate(4, Date.valueOf(dob));

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Saved user successfully");
    }
    public static User getUser(int id){
        connectSqlServer();
        User user  = new User() ;
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users" +
                    " WHERE ID = " + id ;

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                user.setId(rs.getInt("ID"));
                user.setDob(rs.getDate("DOB").toLocalDate());
                user.setFirstName((rs.getString("FIRSTNAME")));
                user.setLastName(rs.getString("LASTNAME"));
                if(user.getId()==id) {
                    return user;
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("User retrieved successfully");
        return user;
    }

    public static Boolean hasId(int id){
        connectSqlServer();
        Boolean hasId = false;
        try {
            String sql = "SELECT * FROM users WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                hasId = true;
            preparedStatement.close();
            connection.close();
            return hasId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasId;
    }
}
