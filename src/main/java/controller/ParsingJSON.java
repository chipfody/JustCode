package controller;

import kong.unirest.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Set;

public class ParsingJSON {


    public void makeMonthObjects(String symbol){

        String url = ApiController.fetchApiQuery(ApiController.createApiQuery(symbol));

        try {
            URLConnection request = new URL(url).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject stock = new JSONObject(response.toString());
            JSONObject meta = stock.getJSONObject("Meta Data");
            JSONObject monthly = stock.getJSONObject("Monthly Time Series");

            Set<String> setMonth = monthly.keySet();
            Iterator monthIterator = setMonth.iterator();
            while (monthIterator.hasNext()){
                JSONObject month = monthly.getJSONObject((String) monthIterator.next());
                System.out.println(month.getString("1. open"));
            }

        } catch (Exception e){
            System.err.println(e.getMessage() );
            System.exit(0);
        }
    }
}
