package controller;

import kong.unirest.json.JSONObject;
import java.util.Iterator;
import java.util.Set;


public class ParsingJSON {

    private String symbol;
    private String dateOfMonth;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;


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
                open = monthlyJSONObject.getString("1. open");
                high = monthlyJSONObject.getString("2. high");
                low = monthlyJSONObject.getString("3. low");
                close = monthlyJSONObject.getString("4. close");
                volume = monthlyJSONObject.getString("5. volume");
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

    public String getOpen() {
        return this.open;
    }

    public String getHigh() {
        return this.high;
    }

    public String getLow() {
        return this.low;
    }

    public String getClose() {
        return this.close;
    }

    public String getVolume() {
        return this.volume;
    }
}
