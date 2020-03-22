package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class GetStocks {
    public static String[] startingStocks = new String[]{"AAPL","AMZN","BP","COKE","COST","CVS","CVX","DPZ","GOOGL","HD","IBM","JNJ","MSFT","MTB","NFLX","TGT","ULTA","V","WMT","XOM"};

    public static Map<String, Double> getAllStocksPrices(String month,String[] stocks){
        Map<String,Double> result = new TreeMap<>();
        try {
            Statement st = SqlController.getConnection().createStatement();
            for (String s : stocks) {
                ResultSet rs = st.executeQuery("SELECT * FROM " + s.toLowerCase() + " WHERE month = '" + month +"'");
                while(rs.next()){
                    result.put(rs.getString("ticker"),rs.getDouble("close"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
