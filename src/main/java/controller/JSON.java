package controller;

import kong.unirest.json.JSONObject;
import sql.SqlController;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class JSON {

    private String symbol;
    private String dateOfMonth;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private String volume;

    public void parsingJSON(String JSON) {

        try {
            JSONObject stock = new JSONObject(JSON);
            JSONObject meta = stock.getJSONObject("Meta Data");
            JSONObject monthly = stock.getJSONObject("Monthly Time Series");

            symbol = meta.getString("2. Symbol");
            SqlController.createTable(symbol);
            Set<String> setMonth = monthly.keySet();
            Stream.of(setMonth)
                    .flatMap(l -> l.stream())
                    .sorted()
                    .forEach(s -> {
                        JSONObject monthlyJSONObject = monthly.getJSONObject(s);
                        dateOfMonth = s;
                        open = Double.valueOf(monthlyJSONObject.getString("1. open"));
                        high = Double.valueOf(monthlyJSONObject.getString("2. high"));
                        low = Double.valueOf(monthlyJSONObject.getString("3. low"));
                        close = Double.valueOf(monthlyJSONObject.getString("4. close"));
                        volume = monthlyJSONObject.getString("5. volume");
                        try {
                            SqlController.insertStock(symbol, dateOfMonth, open, high, low, close, volume);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public void makingJSONFile(String JSON) {

        try {
            FileWriter file = new FileWriter(new File("/Users/hlin/Documents/Projects/Group.Projects/JustCode/src/main/resources/"
                    + getSymbol() + ".json"));
            file.write(JSON);
            file.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public void populateDB(String...symb){
        Stream.of(symb).forEach(s -> {
            parsingJSON(ApiController.fetchApiQuery(ApiController.createApiQuery(s)));
        });
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getDateOfMonth() {
        return this.dateOfMonth;
    }

    public Double getOpen() {
        return this.open;
    }

    public Double getHigh() {
        return this.high;
    }

    public Double getLow() {
        return this.low;
    }

    public Double getClose() {
        return this.close;
    }

    public String getVolume() {
        return this.volume;
    }
}
