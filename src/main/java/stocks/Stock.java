package stocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    public final String symbol;
    public final String name;
    public  String info;
    public Double currentStockPrice;
    public Integer totalNumOfShares;
    public Double valueOfPosition;
    private Map<String, Transaction> transactionHistory;

    public Stock(String symbol, String name){
        this.symbol = symbol;
        this.name = name;
        transactionHistory = new LinkedHashMap<>();
        info = "";
        currentStockPrice = 0.0;
        totalNumOfShares = 0;
        valueOfPosition = 0.0;
    }

    public String getSymbol(){
        return symbol;
    }

    public String getName(){
        return name;
    }

    public String getInfo(){
        return info;
    }

    public Double getCurrentStockPrice(){
        return currentStockPrice;
    }

    public Double getValueOfPosition(){
        return valueOfPosition;
    }

    public Integer getTotalNumOfShares(){
        return totalNumOfShares;
    }

//    public Boolean addTransaction(Transaction newTransaction){
//        return transactionHistory.put(newTransaction.getDateOfTrade, newTransaction);
//    }
//
//    public Transaction getTransaction(String date){
//        return transactionHistory.get(date);
//    }
}