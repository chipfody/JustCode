package controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class ApiController {

    public static String createApiQuery(String symbol){
        StringBuilder builder = new StringBuilder();
        //beginning part of the get() method
        builder.append("https://alpha-vantage.p.rapidapi.com/query?datatype=json&symbol=")
                .append(symbol.toUpperCase())
                .append("&function=TIME_SERIES_MONTHLY");

        return builder.toString();
    }

    public static String fetchApiQuery(String yourQuery){
        HttpResponse<String> response = Unirest.get(yourQuery)
                .header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
                .header("x-rapidapi-key", "9c88018860msh40e297080f45ec6p13b769jsnc02453b1e456")
                .asString();
        response.getBody();
        return response.getBody();
    }

}
