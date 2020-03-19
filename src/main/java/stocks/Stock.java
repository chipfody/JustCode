package stocks;

import org.decimal4j.util.DoubleRounder;
import sql.SqlController;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    public final String symbol;
    public final String name;
    public  String info;
    public Double currentStockPrice;
    public Integer totalNumOfShares;
    public Double valueOfPosition;
    private List<Transaction> transactionHistory;

    public Stock(String symbol, String name){
        this.symbol = symbol;
        this.name = name;
        transactionHistory = new ArrayList<>();
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

    public void setInfo(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }

    public Double getCurrentStockPrice(){
        return currentStockPrice;
    }

    public void updateCurrentStockPrice(String stockSymbol,String month){
        currentStockPrice = checkStockPrice(stockSymbol,month);
        valueOfPosition = DoubleRounder.round(totalNumOfShares * currentStockPrice,2);
    }

    public static Double checkStockPrice(String stockSymbol,String month){
        return SqlController.getStock(stockSymbol,month).getClose();
    }

    public Double getValueOfPosition(){
        return valueOfPosition;
    }

    public void setTotalNumOfShares(Integer newNumShares){
        totalNumOfShares = newNumShares;
    }

    public Integer getTotalNumOfShares(){
        return totalNumOfShares;
    }

    public Boolean addTransaction(Transaction newTransaction){
        if(transactionHistory.contains(newTransaction))
                return false;
        else {
            transactionHistory.add(newTransaction);
            Integer totalShares = 0;
            for(Transaction t : transactionHistory){
                totalShares += t.getNumOfShare();
            }
            setTotalNumOfShares(totalShares);
            return true;
        }
    }

    public Transaction getTransaction(String date){
        Transaction retTransaction = null;
        try {
            for (Transaction t : transactionHistory) {
                if (t.getDateOfTrade().equals(date))
                    retTransaction = t;
            }
            return retTransaction;
        }catch (NullPointerException e){
            System.out.println("Error : " + date + " transaction not found!");
        }
        return retTransaction;
    }
}