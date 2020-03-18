package controller;

import kong.unirest.json.JSONObject;
import java.util.Iterator;
import java.util.Set;


public class ParsingJSON {

    private String symbol;
    private String dateOfMonth;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Integer volume;


    public void makeMonthObjects(String JSON){

        try {
            JSONObject stock = new JSONObject(JSON);
            JSONObject meta = stock.getJSONObject("Meta Data");
            JSONObject monthly = stock.getJSONObject("Monthly Time Series");

            symbol = meta.getString("2. Symbol");

            Set<String> setMonth = monthly.keySet();
            Iterator monthIterator = setMonth.iterator();
            while (monthIterator.hasNext()){
                Object o = monthIterator.next();
                JSONObject monthlyJSONObject = monthly.getJSONObject((String) o);

                dateOfMonth = (String) o;
                open = Double.valueOf(monthlyJSONObject.getString("1. open"));
                high = Double.valueOf(monthlyJSONObject.getString("2. high"));
                low = Double.valueOf(monthlyJSONObject.getString("3. low"));
                close = Double.valueOf(monthlyJSONObject.getString("4. close"));
                volume = Integer.valueOf(monthlyJSONObject.getString("5. volume"));
                monthIterator.next();

            }

        } catch (Exception e){
            System.err.println(e.getMessage() );
            System.exit(0);
        }
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

    public Integer getVolume() {
        return this.volume;
    }
}
